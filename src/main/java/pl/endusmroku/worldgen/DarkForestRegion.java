package pl.endusmroku.worldgen;

/**
 * Deterministic irregular polygon regions for the Dark Forest biome.
 * Each patch gets its own independently chosen width, height and outline.
 */
public final class DarkForestRegion {
    private static final int PATCH_SPACING = 2000;
    private static final double CENTER_JITTER = 260.0;
    private static final double MIN_CENTER_DISTANCE_FROM_ORIGIN = 1100.0;
    private static final double EDGE_BLEND_WIDTH = 128.0;
    private static final int MIN_VERTICES = 5;
    private static final int MAX_VERTICES = 10;

    private DarkForestRegion() {
    }

    public static boolean isInside(double x, double z) {
        int cellX = (int) Math.floor(x / PATCH_SPACING);
        int cellZ = (int) Math.floor(z / PATCH_SPACING);

        for (int gx = cellX - 1; gx <= cellX + 1; gx++) {
            for (int gz = cellZ - 1; gz <= cellZ + 1; gz++) {
                if (signedDistance(x, z, gx, gz) >= 0.0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Smooth terrain fade measured inward from the exact same polygon boundary
     * used by biome selection. The biome registry itself still has a discrete edge.
     */
    public static double edgeFade(double x, double z) {
        int cellX = (int) Math.floor(x / PATCH_SPACING);
        int cellZ = (int) Math.floor(z / PATCH_SPACING);
        double best = 0.0;

        for (int gx = cellX - 1; gx <= cellX + 1; gx++) {
            for (int gz = cellZ - 1; gz <= cellZ + 1; gz++) {
                double distance = signedDistance(x, z, gx, gz);
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

    private static double signedDistance(double x, double z, int gx, int gz) {
        if (!isValidPatch(gx, gz)) {
            return -Double.MAX_VALUE;
        }

        int count = vertexCount(gx, gz);
        double[] vx = new double[count];
        double[] vz = new double[count];

        double halfWidth = dimension(gx, gz, true) * 0.5;
        double halfHeight = dimension(gx, gz, false) * 0.5;
        double maxX = 0.0;
        double maxZ = 0.0;

        for (int i = 0; i < count; i++) {
            double angle = vertexAngle(gx, gz, i, count);
            double radius = vertexRadius(gx, gz, i);
            vx[i] = Math.cos(angle) * radius;
            vz[i] = Math.sin(angle) * radius;
            maxX = Math.max(maxX, Math.abs(vx[i]));
            maxZ = Math.max(maxZ, Math.abs(vz[i]));
        }

        double cx = centerX(gx, gz);
        double cz = centerZ(gx, gz);
        for (int i = 0; i < count; i++) {
            vx[i] = cx + vx[i] / maxX * halfWidth;
            vz[i] = cz + vz[i] / maxZ * halfHeight;
        }

        boolean inside = false;
        double minDistanceSquared = Double.MAX_VALUE;

        for (int i = 0, j = count - 1; i < count; j = i++) {
            double ax = vx[j];
            double az = vz[j];
            double bx = vx[i];
            double bz = vz[i];

            if ((az > z) != (bz > z)
                    && x < (bx - ax) * (z - az) / (bz - az) + ax) {
                inside = !inside;
            }

            double abx = bx - ax;
            double abz = bz - az;
            double lengthSquared = abx * abx + abz * abz;
            double t = lengthSquared == 0.0 ? 0.0
                    : ((x - ax) * abx + (z - az) * abz) / lengthSquared;
            t = Math.max(0.0, Math.min(1.0, t));
            double dx = x - (ax + t * abx);
            double dz = z - (az + t * abz);
            minDistanceSquared = Math.min(minDistanceSquared, dx * dx + dz * dz);
        }

        double distance = Math.sqrt(minDistanceSquared);
        return inside ? distance : -distance;
    }

    private static boolean isValidPatch(int gx, int gz) {
        double cx = centerX(gx, gz);
        double cz = centerZ(gx, gz);
        return cx * cx + cz * cz
                >= MIN_CENTER_DISTANCE_FROM_ORIGIN * MIN_CENTER_DISTANCE_FROM_ORIGIN;
    }

    private static int vertexCount(int gx, int gz) {
        double value = (hash(gx + 101, gz - 211) + 1.0) * 0.5;
        return MIN_VERTICES + (int) Math.floor(value * (MAX_VERTICES - MIN_VERTICES + 1));
    }

    private static double dimension(int gx, int gz, boolean width) {
        double value = width ? hash(gx + 307, gz + 419) : hash(gx - 521, gz + 613);
        return 200.0 + (value + 1.0) * 225.0;
    }

    private static double vertexAngle(int gx, int gz, int index, int count) {
        double base = (Math.PI * 2.0 * index) / count;
        double jitter = hash(gx + index * 37 + 701, gz - index * 53 - 809)
                * (Math.PI / count) * 0.48;
        return base + jitter;
    }

    private static double vertexRadius(int gx, int gz, int index) {
        // Unequal radii and uneven angular spacing create corners, bays and
        // long protrusions instead of a circular outline.
        return 0.62 + (hash(gx + index * 97 + 907, gz - index * 71 - 1009) + 1.0) * 0.19;
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
}
