package pl.endusmroku.worldgen;

/**
 * Deterministic, noise-warped regions used by the Dark Forest biome.
 *
 * The outline is built from a warped distance field instead of polygon vertices
 * or repeating angular waves. Biome selection and terrain blending use this
 * exact same field.
 */
public final class DarkForestRegion {
    private static final int PATCH_SPACING = 2000;
    private static final double CENTER_JITTER = 260.0;
    private static final double MIN_CENTER_DISTANCE_FROM_ORIGIN = 1100.0;
    private static final double EDGE_BLEND_WIDTH = 128.0;

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
     * Returns 0 at the biome border and smoothly rises to 1 inside the biome.
     * This is used for terrain shaping, not for blending biome registry IDs.
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

    /**
     * Positive values are inside. Low-frequency domain warping creates broad,
     * asymmetrical bays and peninsulas; smaller noise layers add restrained
     * natural variation without producing sharp polygon corners.
     */
    private static double signedDistance(double x, double z, int gx, int gz) {
        if (!isValidPatch(gx, gz)) {
            return -Double.MAX_VALUE;
        }

        double cx = centerX(gx, gz);
        double cz = centerZ(gx, gz);
        double aspect = aspect(gx, gz);
        double dx = (x - cx) / aspect;
        double dz = (z - cz) * aspect;

        double seedX = gx * 173.0 + gz * 59.0;
        double seedZ = gz * 197.0 - gx * 83.0;

        // Warp the coordinates before measuring the distance. This breaks the
        // circular silhouette into large, smoothly connected organic lobes.
        double warpX = valueNoise(dx * 0.0028 + seedX, dz * 0.0028 + seedZ) * 62.0;
        double warpZ = valueNoise(dx * 0.0028 + seedX + 37.4, dz * 0.0028 + seedZ - 19.6) * 62.0;

        double wx = dx + warpX;
        double wz = dz + warpZ;
        double radius = Math.sqrt(wx * wx + wz * wz);

        double broad = valueNoise(wx * 0.0038 + seedX + 101.0, wz * 0.0038 + seedZ - 71.0);
        double medium = valueNoise(wx * 0.0090 + seedX - 211.0, wz * 0.0090 + seedZ + 163.0);
        double detail = valueNoise(wx * 0.0190 + seedX + 307.0, wz * 0.0190 + seedZ - 251.0);

        double boundary = 265.0 + broad * 38.0 + medium * 13.0 + detail * 4.0;
        return boundary - radius;
    }

    private static boolean isValidPatch(int gx, int gz) {
        double cx = centerX(gx, gz);
        double cz = centerZ(gx, gz);
        return cx * cx + cz * cz
                >= MIN_CENTER_DISTANCE_FROM_ORIGIN * MIN_CENTER_DISTANCE_FROM_ORIGIN;
    }

    private static double centerX(int gx, int gz) {
        return gx * (double) PATCH_SPACING + hash(gx + 17, gz - 31) * CENTER_JITTER;
    }

    private static double centerZ(int gx, int gz) {
        return gz * (double) PATCH_SPACING + hash(gx - 47, gz + 11) * CENTER_JITTER;
    }

    private static double aspect(int gx, int gz) {
        return 0.78 + (hash(gx + 911, gz - 613) + 1.0) * 0.22;
    }

    /**
     * Smooth value noise with quintic interpolation. Coordinates are local to
     * the patch and seeded per patch, so every region has a different outline.
     */
    private static double valueNoise(double x, double z) {
        int x0 = (int) Math.floor(x);
        int z0 = (int) Math.floor(z);
        double tx = smootherStep(x - x0);
        double tz = smootherStep(z - z0);

        double a = lerp(hash(x0, z0), hash(x0 + 1, z0), tx);
        double b = lerp(hash(x0, z0 + 1), hash(x0 + 1, z0 + 1), tx);
        return lerp(a, b, tz);
    }

    private static double hash(int x, int z) {
        long n = (long) x * 341873128712L + (long) z * 132897987541L;
        n = (n ^ (n >> 13)) * 1274126177L;
        n ^= n >> 16;
        return ((n & 0x7fffffffL) / 1073741823.5) - 1.0;
    }

    private static double smootherStep(double value) {
        return value * value * value * (value * (value * 6.0 - 15.0) + 10.0);
    }

    private static double lerp(double a, double b, double delta) {
        return a + (b - a) * delta;
    }
}
