package pl.enderwilds.worldgen;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Deterministic, independently placed Ashenwood regions. Patches use the same
 * dimensions and boundary style as Endergrove, but use independently jittered
 * candidate centers and reject centers that would overlap Endergrove patches.
 */
public final class AshenwoodRegion {
    private static final int PATCH_SPACING = 2000;
    private static final double CENTER_JITTER = 450.0;
    private static final double MIN_CENTER_DISTANCE_FROM_ORIGIN = 1100.0;
    private static final double EDGE_BLEND_WIDTH = 128.0;

    private static final ConcurrentMap<Long, Patch> PATCH_CACHE = new ConcurrentHashMap<>();

    private AshenwoodRegion() {
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

    /** Smooth terrain fade measured inward from the same noise-defined boundary. */
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

        double width = dimension(gx, gz, true);
        double height = dimension(gx, gz, false);
        if (!EndergroveRegion.isPatchClearOfEndergrove(
                cx, cz, width * 0.5, height * 0.5)) {
            return Patch.EMPTY;
        }
        int seedX = gx * 734287 + gz * 912931 + 173;
        int seedZ = gz * 438289 - gx * 651731 - 419;

        return new Patch(cx, cz, width * 0.5, height * 0.5, seedX, seedZ);
    }

    private static double dimension(int gx, int gz, boolean width) {
        double value = width ? hash(gx + 307, gz + 419) : hash(gx - 521, gz + 613);
        return 240.0 + (value + 1.0) * 230.0;
    }

    private static double centerX(int gx, int gz) {
        return (gx + 0.5) * PATCH_SPACING + hash(gx + 211, gz - 431) * CENTER_JITTER;
    }

    private static double centerZ(int gx, int gz) {
        return (gz + 0.5) * PATCH_SPACING + hash(gx - 547, gz + 613) * CENTER_JITTER;
    }

    private static double valueNoise2D(double x, double z, int seedX, int seedZ) {
        int x0 = (int) Math.floor(x);
        int z0 = (int) Math.floor(z);
        double tx = smooth(x - x0);
        double tz = smooth(z - z0);

        double a = lerp(hash(x0 + seedX, z0 + seedZ),
                hash(x0 + 1 + seedX, z0 + seedZ), tx);
        double b = lerp(hash(x0 + seedX, z0 + 1 + seedZ),
                hash(x0 + 1 + seedX, z0 + 1 + seedZ), tx);
        return lerp(a, b, tz);
    }

    private static double hash(int x, int z) {
        x += 1973;
        z -= 3251;
        long n = (long) x * 341873128712L + (long) z * 132897987541L;
        n = (n ^ (n >> 13)) * 1274126177L;
        n ^= n >> 16;
        return ((n & 0x7fffffffL) / 1073741823.5) - 1.0;
    }

    private static double smooth(double value) {
        return value * value * (3.0 - 2.0 * value);
    }

    private static double lerp(double a, double b, double delta) {
        return a + (b - a) * delta;
    }

    private static final class Patch {
        private static final Patch EMPTY = new Patch(0.0, 0.0, 0.0, 0.0, 0, 0);

        private final double centerX;
        private final double centerZ;
        private final double halfWidth;
        private final double halfHeight;
        private final double distanceScale;
        private final int seedX;
        private final int seedZ;
        private final boolean empty;

        private Patch(double centerX, double centerZ, double halfWidth, double halfHeight,
                      int seedX, int seedZ) {
            this.centerX = centerX;
            this.centerZ = centerZ;
            this.halfWidth = halfWidth;
            this.halfHeight = halfHeight;
            this.distanceScale = (halfWidth + halfHeight) * 0.5;
            this.seedX = seedX;
            this.seedZ = seedZ;
            this.empty = halfWidth == 0.0 || halfHeight == 0.0;
        }

        private double signedDistance(double x, double z) {
            if (empty) {
                return -Double.MAX_VALUE;
            }

            double u = (x - centerX) / halfWidth;
            double v = (z - centerZ) / halfHeight;

            // Warp the coordinates before sampling the boundary noise. This
            // creates irregular coves and protrusions instead of a simple oval.
            double warpX = valueNoise2D(u * 2.7 + 13.0, v * 2.7 - 29.0,
                    seedX + 101, seedZ - 211) * 0.27;
            double warpZ = valueNoise2D(u * 2.7 - 37.0, v * 2.7 + 53.0,
                    seedX - 307, seedZ + 419) * 0.27;
            double warpedU = u + warpX;
            double warpedV = v + warpZ;

            double broad = valueNoise2D(warpedU * 3.1 + 17.0, warpedV * 3.1 - 31.0,
                    seedX + 701, seedZ - 809);
            double medium = valueNoise2D(warpedU * 6.4 - 43.0, warpedV * 6.4 + 59.0,
                    seedX + 907, seedZ - 1009);
            double fine = valueNoise2D(warpedU * 12.8 + 71.0, warpedV * 12.8 - 89.0,
                    seedX - 1103, seedZ + 1201);

            // A weak central falloff keeps each patch one landmass. Strong,
            // differently scaled noise overwhelms the smooth oval outline,
            // producing asymmetric inlets, narrow necks and uneven lobes.
            double base = 1.0 - Math.sqrt(warpedU * warpedU + warpedV * warpedV);
            double field = base + broad * 0.30 + medium * 0.15 + fine * 0.055;
            return field * distanceScale;
        }
    }
}
