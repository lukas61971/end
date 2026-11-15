package pl.endusmroku.worldgen;

/**
 * Deterministic, irregular polygon regions used by the Dark Forest biome.
 * The same geometry is shared by biome selection and terrain shaping.
 */
public final class DarkForestRegion {
    private static final int PATCH_SPACING = 2000;
    private static final double CENTER_JITTER = 320.0;
    private static final double MIN_CENTER_DISTANCE_FROM_ORIGIN = 1100.0;
    private static final double EDGE_BLEND_WIDTH = 48.0;

    private DarkForestRegion() {
    }

    public static boolean isInside(double x, double z) {
        int cellX = (int) Math.floor(x / PATCH_SPACING);
        int cellZ = (int) Math.floor(z / PATCH_SPACING);

        for (int gx = cellX - 1; gx <= cellX + 1; gx++) {
            for (int gz = cellZ - 1; gz <= cellZ + 1; gz++) {
                if (isInsidePatch(x, z, gx, gz)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static double edgeFade(double x, double z) {
        int cellX = (int) Math.floor(x / PATCH_SPACING);
        int cellZ = (int) Math.floor(z / PATCH_SPACING);
        double best = 0.0;

        for (int gx = cellX - 1; gx <= cellX + 1; gx++) {
            for (int gz = cellZ - 1; gz <= cellZ + 1; gz++) {
                if (!isValidPatch(gx, gz) || !isInsidePatch(x, z, gx, gz)) {
                    continue;
                }

                double edgeDistance = distanceToPatchEdge(x, z, gx, gz);
                double t = Math.max(0.0, Math.min(1.0, edgeDistance / EDGE_BLEND_WIDTH));
                double fade = t * t * (3.0 - 2.0 * t);
                best = Math.max(best, fade);
            }
        }
        return best;
    }

    private static boolean isValidPatch(int gx, int gz) {
        double centerX = gx * (double) PATCH_SPACING + hash(gx + 17, gz - 31) * CENTER_JITTER;
        double centerZ = gz * (double) PATCH_SPACING + hash(gx - 47, gz + 11) * CENTER_JITTER;
        return centerX * centerX + centerZ * centerZ
                >= MIN_CENTER_DISTANCE_FROM_ORIGIN * MIN_CENTER_DISTANCE_FROM_ORIGIN;
    }

    private static boolean isInsidePatch(double x, double z, int gx, int gz) {
        if (!isValidPatch(gx, gz)) {
            return false;
        }

        int count = vertexCount(gx, gz);
        boolean inside = false;

        double previousX = vertexX(gx, gz, count - 1, count);
        double previousZ = vertexZ(gx, gz, count - 1, count);

        for (int i = 0; i < count; i++) {
            double currentX = vertexX(gx, gz, i, count);
            double currentZ = vertexZ(gx, gz, i, count);

            if ((currentZ > z) != (previousZ > z)
                    && x < (previousX - currentX) * (z - currentZ)
                    / (previousZ - currentZ) + currentX) {
                inside = !inside;
            }

            previousX = currentX;
            previousZ = currentZ;
        }
        return inside;
    }

    private static double distanceToPatchEdge(double x, double z, int gx, int gz) {
        int count = vertexCount(gx, gz);
        double minimumSquared = Double.MAX_VALUE;
        double previousX = vertexX(gx, gz, count - 1, count);
        double previousZ = vertexZ(gx, gz, count - 1, count);

        for (int i = 0; i < count; i++) {
            double currentX = vertexX(gx, gz, i, count);
            double currentZ = vertexZ(gx, gz, i, count);
            double segmentX = currentX - previousX;
            double segmentZ = currentZ - previousZ;
            double lengthSquared = segmentX * segmentX + segmentZ * segmentZ;
            double projection = lengthSquared == 0.0 ? 0.0
                    : ((x - previousX) * segmentX + (z - previousZ) * segmentZ) / lengthSquared;
            projection = Math.max(0.0, Math.min(1.0, projection));

            double nearestX = previousX + projection * segmentX;
            double nearestZ = previousZ + projection * segmentZ;
            double dx = x - nearestX;
            double dz = z - nearestZ;
            minimumSquared = Math.min(minimumSquared, dx * dx + dz * dz);

            previousX = currentX;
            previousZ = currentZ;
        }
        return Math.sqrt(minimumSquared);
    }

    private static int vertexCount(int gx, int gz) {
        return 5 + (int) Math.floor((hash(gx + 311, gz + 719) + 1.0) * 12.0);
    }

    private static double centerX(int gx, int gz) {
        return gx * (double) PATCH_SPACING + hash(gx + 17, gz - 31) * CENTER_JITTER;
    }

    private static double centerZ(int gx, int gz) {
        return gz * (double) PATCH_SPACING + hash(gx - 47, gz + 11) * CENTER_JITTER;
    }

    private static double vertexX(int gx, int gz, int index, int count) {
        double angle = vertexAngle(gx, gz, index, count);
        double radius = vertexRadius(gx, gz, index);
        double aspect = 0.72 + (hash(gx + 911, gz - 613) + 1.0) * 0.24;
        return centerX(gx, gz) + Math.cos(angle) * radius * aspect;
    }

    private static double vertexZ(int gx, int gz, int index, int count) {
        double angle = vertexAngle(gx, gz, index, count);
        double radius = vertexRadius(gx, gz, index);
        double aspect = 0.72 + (hash(gx + 911, gz - 613) + 1.0) * 0.24;
        return centerZ(gx, gz) + Math.sin(angle) * radius / aspect;
    }

    private static double vertexAngle(int gx, int gz, int index, int count) {
        double rotation = hash(gx + 101, gz - 203) * Math.PI;
        double step = Math.PI * 2.0 / count;
        double jitter = hash(gx + index * 37 + 401, gz - index * 53 - 809) * 0.16;
        return rotation + index * step + jitter;
    }

    private static double vertexRadius(int gx, int gz, int index) {
        return 235.0 + hash(gx + index * 67 + 1201, gz - index * 43 - 1601) * 105.0;
    }

    private static double hash(int x, int z) {
        long n = (long) x * 341873128712L + (long) z * 132897987541L;
        n = (n ^ (n >> 13)) * 1274126177L;
        n ^= n >> 16;
        return ((n & 0x7fffffffL) / 1073741823.5) - 1.0;
    }
}
