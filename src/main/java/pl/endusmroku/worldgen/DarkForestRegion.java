package pl.endusmroku.worldgen;

/**
 * Smooth, organic regions used by the Dark Forest biome.
 * The same deterministic shape is shared by biome selection and terrain shaping.
 */
public final class DarkForestRegion {
    private static final int PATCH_SPACING = 2000;
    private static final double CENTER_JITTER = 320.0;
    private static final double MIN_CENTER_DISTANCE_FROM_ORIGIN = 1100.0;
    private static final double EDGE_BLEND_WIDTH = 96.0;

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
                if (!isValidPatch(gx, gz)) {
                    continue;
                }

                double distance = signedEdgeDistance(x, z, gx, gz);
                if (distance <= 0.0) {
                    continue;
                }

                double t = Math.max(0.0, Math.min(1.0, distance / EDGE_BLEND_WIDTH));
                double fade = t * t * (3.0 - 2.0 * t);
                best = Math.max(best, fade);
            }
        }
        return best;
    }

    private static boolean isValidPatch(int gx, int gz) {
        double centerX = centerX(gx, gz);
        double centerZ = centerZ(gx, gz);
        return centerX * centerX + centerZ * centerZ
                >= MIN_CENTER_DISTANCE_FROM_ORIGIN * MIN_CENTER_DISTANCE_FROM_ORIGIN;
    }

    private static boolean isInsidePatch(double x, double z, int gx, int gz) {
        return signedEdgeDistance(x, z, gx, gz) >= 0.0;
    }

    /**
     * Positive values are inside the region. A smooth radial contour avoids
     * straight polygon edges, sharp corners and self-intersecting outlines.
     */
    private static double signedEdgeDistance(double x, double z, int gx, int gz) {
        if (!isValidPatch(gx, gz)) {
            return -Double.MAX_VALUE;
        }

        double aspect = aspect(gx, gz);
        double dx = x - centerX(gx, gz);
        double dz = z - centerZ(gx, gz);

        double normalizedX = dx / aspect;
        double normalizedZ = dz * aspect;
        double radius = Math.sqrt(normalizedX * normalizedX + normalizedZ * normalizedZ);
        double angle = Math.atan2(normalizedZ, normalizedX);
        double boundary = boundaryRadius(gx, gz, angle);

        // Convert normalized radial distance back to an approximate block distance.
        return (boundary - radius) * Math.min(aspect, 1.0 / aspect);
    }

    private static double boundaryRadius(int gx, int gz, double angle) {
        double phase1 = hash(gx + 101, gz - 203) * Math.PI;
        double phase2 = hash(gx - 307, gz + 409) * Math.PI;
        double phase3 = hash(gx + 503, gz + 601) * Math.PI;
        double phase4 = hash(gx - 701, gz - 809) * Math.PI;

        // Broad lobes define the overall silhouette; smaller waves add natural variation.
        return 255.0
                + Math.sin(angle * 2.0 + phase1) * 42.0
                + Math.sin(angle * 3.0 + phase2) * 25.0
                + Math.sin(angle * 5.0 + phase3) * 14.0
                + Math.sin(angle * 8.0 + phase4) * 7.0;
    }

    private static double centerX(int gx, int gz) {
        return gx * (double) PATCH_SPACING + hash(gx + 17, gz - 31) * CENTER_JITTER;
    }

    private static double centerZ(int gx, int gz) {
        return gz * (double) PATCH_SPACING + hash(gx - 47, gz + 11) * CENTER_JITTER;
    }

    private static double aspect(int gx, int gz) {
        return 0.82 + (hash(gx + 911, gz - 613) + 1.0) * 0.16;
    }

    private static double hash(int x, int z) {
        long n = (long) x * 341873128712L + (long) z * 132897987541L;
        n = (n ^ (n >> 13)) * 1274126177L;
        n ^= n >> 16;
        return ((n & 0x7fffffffL) / 1073741823.5) - 1.0;
    }
}
