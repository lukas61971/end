package pl.endusmroku.worldgen;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Deterministic organic biome patches. Each patch is a closed Catmull-Rom
 * spline through many irregular control points, scaled to independently
 * selected width and height. The same outline drives biome selection and terrain.
 */
public final class DarkForestRegion {
    private static final int PATCH_SPACING = 2000;
    private static final double CENTER_JITTER = 260.0;
    private static final double MIN_CENTER_DISTANCE_FROM_ORIGIN = 1100.0;
    private static final double EDGE_BLEND_WIDTH = 128.0;

    // A large number of unevenly spaced points prevents the outline from
    // collapsing into a simple circle, oval or low-sided polygon.
    private static final int MIN_CONTROL_POINTS = 44;
    private static final int MAX_CONTROL_POINTS = 58;
    private static final int CURVE_STEPS = 5;

    private static final ConcurrentMap<Long, Patch> PATCH_CACHE = new ConcurrentHashMap<>();

    private DarkForestRegion() {
    }

    public static boolean isInside(double x, double z) {
        int cellX = (int) Math.floor(x / PATCH_SPACING);
        int cellZ = (int) Math.floor(z / PATCH_SPACING);

        for (int gx = cellX - 1; gx <= cellX + 1; gx++) {
            for (int gz = cellZ - 1; gz <= cellZ + 1; gz++) {
                if (patch(gx, gz).signedDistance(x, z) >= 0.0) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Smooth terrain fade measured inward from the exact same curved boundary. */
    public static double edgeFade(double x, double z) {
        int cellX = (int) Math.floor(x / PATCH_SPACING);
        int cellZ = (int) Math.floor(z / PATCH_SPACING);
        double best = 0.0;

        for (int gx = cellX - 1; gx <= cellX + 1; gx++) {
            for (int gz = cellZ - 1; gz <= cellZ + 1; gz++) {
                double distance = patch(gx, gz).signedDistance(x, z);
                if (distance <= 0.0) {
                    continue;
                }

                double t = Math.min(1.0, distance / EDGE_BLEND_WIDTH);
                double fade = t * t * t * (t * (t * 6.0 - 15.0) + 10.0);
                best = Math.max(best, fade);
            }
        }
        return best;
    }

    private static Patch patch(int gx, int gz) {
        long key = ((long) gx << 32) ^ (gz & 0xffffffffL);
        return PATCH_CACHE.computeIfAbsent(key, ignored -> createPatch(gx, gz));
    }

    private static Patch createPatch(int gx, int gz) {
        double cx = centerX(gx, gz);
        double cz = centerZ(gx, gz);

        if (cx * cx + cz * cz
                < MIN_CENTER_DISTANCE_FROM_ORIGIN * MIN_CENTER_DISTANCE_FROM_ORIGIN) {
            return Patch.EMPTY;
        }

        int count = controlPointCount(gx, gz);
        double[] controlX = new double[count];
        double[] controlZ = new double[count];
        double maxX = 0.0;
        double maxZ = 0.0;

        // Keep the points in angular order to form one continuous landmass,
        // but vary both the angle spacing and radius heavily. The wide radius
        // range creates deep bays, narrow necks, bulges and long protrusions.
        double angle = hash(gx + 1301, gz - 1709) * Math.PI;
        for (int i = 0; i < count; i++) {
            double baseStep = Math.PI * 2.0 / count;
            double angleVariation = hash(gx + 37 * i + 701, gz - 53 * i - 809)
                    * baseStep * 0.72;
            angle += baseStep + angleVariation;

            double broadShape = hash(gx * 3 + i / 5 + 1901, gz * 3 - i / 5 - 2017);
            double localShape = hash(gx + 97 * i + 907, gz - 71 * i - 1009);
            double fineShape = hash(gx - 131 * i - 2203, gz + 113 * i + 2309);

            // Combine slower lobes with sharp local variation. This is
            // intentionally much less circular than a conventional radial mask.
            double radius = 0.16
                    + (broadShape + 1.0) * 0.20
                    + (localShape + 1.0) * 0.22
                    + (fineShape + 1.0) * 0.12;
            radius = Math.max(0.12, Math.min(1.0, radius));

            controlX[i] = Math.cos(angle) * radius;
            controlZ[i] = Math.sin(angle) * radius;
            maxX = Math.max(maxX, Math.abs(controlX[i]));
            maxZ = Math.max(maxZ, Math.abs(controlZ[i]));
        }

        // Independent dimensions: neither axis is derived from the other.
        double width = dimension(gx, gz, true);
        double height = dimension(gx, gz, false);
        double halfWidth = width * 0.5;
        double halfHeight = height * 0.5;

        for (int i = 0; i < count; i++) {
            controlX[i] = cx + controlX[i] / maxX * halfWidth;
            controlZ[i] = cz + controlZ[i] / maxZ * halfHeight;
        }

        // Sample the smooth closed spline densely enough that point-in-polygon
        // and distance checks follow the curved outline rather than its controls.
        int samples = count * CURVE_STEPS;
        double[] outlineX = new double[samples];
        double[] outlineZ = new double[samples];

        for (int i = 0; i < count; i++) {
            double x0 = controlX[(i - 1 + count) % count];
            double z0 = controlZ[(i - 1 + count) % count];
            double x1 = controlX[i];
            double z1 = controlZ[i];
            double x2 = controlX[(i + 1) % count];
            double z2 = controlZ[(i + 1) % count];
            double x3 = controlX[(i + 2) % count];
            double z3 = controlZ[(i + 2) % count];

            for (int step = 0; step < CURVE_STEPS; step++) {
                double t = step / (double) CURVE_STEPS;
                int index = i * CURVE_STEPS + step;
                outlineX[index] = catmullRom(x0, x1, x2, x3, t);
                outlineZ[index] = catmullRom(z0, z1, z2, z3, t);
            }
        }

        return new Patch(outlineX, outlineZ);
    }

    private static int controlPointCount(int gx, int gz) {
        double value = (hash(gx + 101, gz - 211) + 1.0) * 0.5;
        return MIN_CONTROL_POINTS
                + (int) Math.floor(value * (MAX_CONTROL_POINTS - MIN_CONTROL_POINTS + 1));
    }

    private static double dimension(int gx, int gz, boolean width) {
        double value = width ? hash(gx + 307, gz + 419) : hash(gx - 521, gz + 613);
        return 200.0 + (value + 1.0) * 225.0;
    }

    private static double catmullRom(double p0, double p1, double p2, double p3, double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        return 0.5 * ((2.0 * p1)
                + (-p0 + p2) * t
                + (2.0 * p0 - 5.0 * p1 + 4.0 * p2 - p3) * t2
                + (-p0 + 3.0 * p1 - 3.0 * p2 + p3) * t3);
    }

    private static double centerX(int gx, int gz) {
        return gx * (double) PATCH_SPACING + hash(gx + 17, gz - 31) * CENTER_JITTER;
    }

    private static double centerZ(int gx, int gz) {
        return gz * (double) PATCH_SPACING + hash(gx - 47, gz + 11) * CENTER_JITTER;
    }

    private static double hash(int x, int z) {
        long n = (long) x * 341873128712L + (long) z * 132897987541L;
        n = (n ^ (n >> 13)) * 1274126177L;
        n ^= n >> 16;
        return ((n & 0x7fffffffL) / 1073741823.5) - 1.0;
    }

    private static final class Patch {
        private static final Patch EMPTY = new Patch(new double[0], new double[0]);

        private final double[] x;
        private final double[] z;

        private Patch(double[] x, double[] z) {
            this.x = x;
            this.z = z;
        }

        private double signedDistance(double px, double pz) {
            if (x.length == 0) {
                return -Double.MAX_VALUE;
            }

            boolean inside = false;
            double minDistanceSquared = Double.MAX_VALUE;

            for (int i = 0, j = x.length - 1; i < x.length; j = i++) {
                double ax = x[j];
                double az = z[j];
                double bx = x[i];
                double bz = z[i];

                if ((az > pz) != (bz > pz)
                        && px < (bx - ax) * (pz - az) / (bz - az) + ax) {
                    inside = !inside;
                }

                double abx = bx - ax;
                double abz = bz - az;
                double lengthSquared = abx * abx + abz * abz;
                double t = lengthSquared == 0.0 ? 0.0
                        : ((px - ax) * abx + (pz - az) * abz) / lengthSquared;
                t = Math.max(0.0, Math.min(1.0, t));
                double dx = px - (ax + t * abx);
                double dz = pz - (az + t * abz);
                minDistanceSquared = Math.min(minDistanceSquared, dx * dx + dz * dz);
            }

            double distance = Math.sqrt(minDistanceSquared);
            return inside ? distance : -distance;
        }
    }
}
