package pl.endusmroku.mixin;

import net.minecraft.block.BlockState;
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
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.StructureAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.endusmroku.EndusMroku;

@Mixin(NoiseChunkGenerator.class)
public abstract class DarkForestTerrainMixin {
    @Unique
    private static final RegistryKey<Biome> ENDUSMROKU_DARK_FOREST_KEY = RegistryKey.of(
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
                        .matchesKey(ENDUSMROKU_DARK_FOREST_KEY)) {
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

                double broad = endusmroku$valueNoise2D(x * 0.012, z * 0.012);
                double detail = endusmroku$valueNoise2D(x * 0.045 + 173.0, z * 0.045 - 91.0);
                double ridgeNoise = endusmroku$valueNoise2D(x * 0.022 - 307.0, z * 0.022 + 419.0);
                double ridge = 1.0 - Math.abs(ridgeNoise);

                int heightChange = (int) Math.round(
                        (broad * 12.0 + detail * 5.0 + ridge * ridge * 14.0 - 6.0) * fade
                );
                heightChange = Math.max(-18, Math.min(22, heightChange));

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

                // Carve small, irregular underground chambers into the new terrain.
                for (int y = bottomY + 8; y < newSurfaceY - 4; y++) {
                    double caveA = endusmroku$valueNoise3D(x * 0.045, y * 0.055, z * 0.045);
                    double caveB = endusmroku$valueNoise3D(x * 0.085 + 61.0, y * 0.095, z * 0.085 - 37.0);
                    if (caveA * 0.72 + caveB * 0.28 > 0.57) {
                        pos.set(x, y, z);
                        if (chunk.getBlockState(pos).isOf(Blocks.END_STONE)) {
                            chunk.setBlockState(pos, Blocks.AIR.getDefaultState());
                        }
                    }
                }

                // The surface of the biome is dark nylium; the terrain beneath remains end stone.
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
            Chunk chunk,
            BlockPos.Mutable pos,
            int x,
            int z,
            int topY,
            int bottomY
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
        double dx = x - 1500.0;
        double dz = z;
        double angle = Math.atan2(dz, dx);
        double radius = 250.0
                + 14.0 * Math.sin(angle * 3.0 + 0.4)
                + 9.0 * Math.sin(angle * 7.0 - 1.2)
                + 6.0 * Math.sin(angle * 11.0 + 0.9);
        double distanceToEdge = radius - Math.sqrt(dx * dx + dz * dz);
        double t = Math.max(0.0, Math.min(1.0, distanceToEdge / 36.0));
        return t * t * (3.0 - 2.0 * t);
    }

    @Unique
    private static double endusmroku$valueNoise2D(double x, double z) {
        int x0 = (int) Math.floor(x);
        int z0 = (int) Math.floor(z);
        double tx = endusmroku$smooth(x - x0);
        double tz = endusmroku$smooth(z - z0);

        double a = endusmroku$lerp(
                endusmroku$hash(x0, z0),
                endusmroku$hash(x0 + 1, z0),
                tx
        );
        double b = endusmroku$lerp(
                endusmroku$hash(x0, z0 + 1),
                endusmroku$hash(x0 + 1, z0 + 1),
                tx
        );
        return endusmroku$lerp(a, b, tz);
    }

    @Unique
    private static double endusmroku$valueNoise3D(double x, double y, double z) {
        int x0 = (int) Math.floor(x);
        int y0 = (int) Math.floor(y);
        int z0 = (int) Math.floor(z);
        double tx = endusmroku$smooth(x - x0);
        double ty = endusmroku$smooth(y - y0);
        double tz = endusmroku$smooth(z - z0);

        double c00 = endusmroku$lerp(endusmroku$hash(x0, y0, z0), endusmroku$hash(x0 + 1, y0, z0), tx);
        double c10 = endusmroku$lerp(endusmroku$hash(x0, y0 + 1, z0), endusmroku$hash(x0 + 1, y0 + 1, z0), tx);
        double c01 = endusmroku$lerp(endusmroku$hash(x0, y0, z0 + 1), endusmroku$hash(x0 + 1, y0, z0 + 1), tx);
        double c11 = endusmroku$lerp(endusmroku$hash(x0, y0 + 1, z0 + 1), endusmroku$hash(x0 + 1, y0 + 1, z0 + 1), tx);

        return endusmroku$lerp(
                endusmroku$lerp(c00, c10, ty),
                endusmroku$lerp(c01, c11, ty),
                tz
        );
    }

    @Unique
    private static double endusmroku$hash(int x, int z) {
        long n = (long) x * 341873128712L + (long) z * 132897987541L;
        n = (n ^ (n >> 13)) * 1274126177L;
        n ^= n >> 16;
        return ((n & 0x7fffffffL) / 1073741823.5) - 1.0;
    }

    @Unique
    private static double endusmroku$hash(int x, int y, int z) {
        long n = (long) x * 341873128712L
                + (long) y * 132897987541L
                + (long) z * 42317861L;
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
