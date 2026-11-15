package pl.endusmroku.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.endusmroku.EndusMroku;

@Mixin(NoiseChunkGenerator.class)
public abstract class DarkForestTerrainMixin {
    @Unique
    private static final RegistryKey<Biome> ENDURO_DARK_FOREST_KEY = RegistryKey.of(
            RegistryKeys.BIOME,
            Identifier.of("endusmroku", "dark_forest")
    );

    @Inject(
            method = "buildSurface(Lnet/minecraft/world/chunk/Chunk;Lnet/minecraft/world/gen/HeightContext;Lnet/minecraft/world/gen/noise/NoiseConfig;Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/biome/source/BiomeAccess;Lnet/minecraft/registry/Registry;Lnet/minecraft/world/gen/chunk/Blender;)V",
            at = @At("TAIL")
    )
    private void endusmroku$shapeDarkForest(
            Chunk chunk,
            HeightContext heightContext,
            NoiseConfig noiseConfig,
            StructureAccessor structureAccessor,
            BiomeAccess biomeAccess,
            Registry<Biome> biomeRegistry,
            Blender blender,
            CallbackInfo ci
    ) {
        int startX = chunk.getPos().getStartX();
        int startZ = chunk.getPos().getStartZ();
        int bottomY = heightContext.getMinY();
        int topY = bottomY + heightContext.getHeight();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int localX = 0; localX < 16; localX++) {
            for (int localZ = 0; localZ < 16; localZ++) {
                int x = startX + localX;
                int z = startZ + localZ;

                if (!biomeAccess.getBiomeForNoiseGen(x >> 2, 0, z >> 2)
                        .matchesKey(ENDURO_DARK_FOREST_KEY)) {
                    continue;
                }

                double fade = endusmroku$edgeFade(x, z);
                if (fade <= 0.001) {
                    continue;
                }

                int surfaceY = endusmroku$findSurface(chunk, pos, x, z, topY, bottomY);
                if (surfaceY < bottomY) {
                    continue;
                }

                // Smooth the height field over neighbouring columns to avoid isolated spikes and pits.
                double smoothedChange = 0.0;
                int totalWeight = 0;
                for (int ox = -1; ox <= 1; ox++) {
                    for (int oz = -1; oz <= 1; oz++) {
                        int weight = (ox == 0 ? 2 : 1) * (oz == 0 ? 2 : 1);
                        smoothedChange += endusmroku$rawHeightChange(x + ox, z + oz) * weight;
                        totalWeight += weight;
                    }
                }

                int heightChange = (int) Math.round((smoothedChange / totalWeight) * fade);
                heightChange = Math.max(-14, Math.min(18, heightChange));
                int newSurfaceY = Math.max(bottomY + 8, Math.min(topY - 3, surfaceY + heightChange));

                if (newSurfaceY > surfaceY) {
                    for (int y = surfaceY + 1; y <= newSurfaceY; y++) {
                        pos.set(x, y, z);
                        chunk.setBlockState(pos, Blocks.END_STONE.getDefaultState());
                    }
                } else if (newSurfaceY < surfaceY) {
                    for (int y = surfaceY; y > newSurfaceY; y--) {
                        pos.set(x, y, z);
                        chunk.setBlockState(pos, Blocks.AIR.getDefaultState());
                    }
                }

                // Keep this stage free of artificial cave carving; it caused thin, unnatural holes.
                pos.set(x, newSurfaceY, z);
                if (chunk.getBlockState(pos).isOf(Blocks.END_STONE)) {
                    chunk.setBlockState(pos, EndusMroku.CIEMNE_NYLIUM.getDefaultState());
                }
            }
        }

        chunk.refreshSurfaceY();
    }

    @Unique
    private static int endusmroku$findSurface(
            Chunk chunk, BlockPos.Mutable pos, int x, int z, int topY, int bottomY
    ) {
        for (int y = topY - 1; y >= bottomY; y--) {
            pos.set(x, y, z);
            if (chunk.getBlockState(pos).isOf(Blocks.END_STONE)) {
                return y;
            }
        }
        return bottomY - 1;
    }

    @Unique
    private static double endusmroku$edgeFade(int x, int z) {
        int cellX = (int) Math.floor(x / 2000.0);
        int cellZ = (int) Math.floor(z / 2000.0);
        double best = 0.0;

        for (int gx = cellX - 1; gx <= cellX + 1; gx++) {
            for (int gz = cellZ - 1; gz <= cellZ + 1; gz++) {
                double seed = endusmroku$hash(gx, gz);
                double centerX = gx * 2000.0 + endusmroku$hash(gx + 17, gz - 31) * 320.0;
                double centerZ = gz * 2000.0 + endusmroku$hash(gx - 47, gz + 11) * 320.0;
                if (centerX * centerX + centerZ * centerZ < 1100.0 * 1100.0) {
                    continue;
                }

                double dx = x - centerX;
                double dz = z - centerZ;
                double angle = Math.atan2(dz, dx);
                double radius = 235.0 + seed * 22.0
                        + 42.0 * Math.sin(angle * 2.0 + seed * 4.0)
                        + 27.0 * Math.sin(angle * 5.0 - seed * 7.0)
                        + 17.0 * Math.sin(angle * 9.0 + seed * 11.0)
                        + 11.0 * Math.sin(angle * 13.0 - seed * 3.0);
                double distanceToEdge = radius - Math.sqrt(dx * dx + dz * dz);
                double t = Math.max(0.0, Math.min(1.0, distanceToEdge / 48.0));
                double fade = t * t * (3.0 - 2.0 * t);
                best = Math.max(best, fade);
            }
        }
        return best;
    }

    @Unique
    private static double endusmroku$rawHeightChange(int x, int z) {
        double broad = endusmroku$valueNoise2D(x * 0.009, z * 0.009);
        double detail = endusmroku$valueNoise2D(x * 0.024 + 173.0, z * 0.024 - 91.0);
        double ridgeNoise = endusmroku$valueNoise2D(x * 0.014 - 307.0, z * 0.014 + 419.0);
        double ridge = 1.0 - Math.abs(ridgeNoise);
        double change = broad * 10.0 + detail * 3.0 + ridge * ridge * 8.0 - 3.0;
        return Math.max(-14.0, Math.min(18.0, change));
    }

    @Unique
    private static double endusmroku$valueNoise2D(double x, double z) {
        int x0 = (int) Math.floor(x);
        int z0 = (int) Math.floor(z);
        double tx = endusmroku$smooth(x - x0);
        double tz = endusmroku$smooth(z - z0);
        double a = endusmroku$lerp(endusmroku$hash(x0, z0), endusmroku$hash(x0 + 1, z0), tx);
        double b = endusmroku$lerp(endusmroku$hash(x0, z0 + 1), endusmroku$hash(x0 + 1, z0 + 1), tx);
        return endusmroku$lerp(a, b, tz);
    }

    @Unique
    private static double endusmroku$hash(int x, int z) {
        long n = (long) x * 341873128712L + (long) z * 132897987541L;
        n = (n ^ (n >> 13)) * 1274126177L;
        n ^= n >> 16;
        return ((n & 0x7fffffffL) / 1073741823.5) - 1.0;
    }

    @Unique
    private static double endusmroku$smooth(double value) {
        return value * value * (3.0 - 2.0 * value);
    }

    @Unique
    private static double endusmroku$lerp(double a, double b, double delta) {
        return a + (b - a) * delta;
    }
}
