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
import pl.endusmroku.worldgen.DarkForestRegion;

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

                double fade = DarkForestRegion.edgeFade(x, z);
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
    private static double endusmroku$rawHeightChange(int x, int z) {
        // Large-scale noise creates broad plains, basins and long valleys.
        double continental = endusmroku$valueNoise2D(x * 0.0045 + 41.0, z * 0.0045 - 73.0);
        // Medium noise breaks the terrain into rolling hills and mountain groups.
        double hills = endusmroku$valueNoise2D(x * 0.011 + 173.0, z * 0.011 - 91.0);
        // Fine noise adds smaller unevenness without making the surface jagged.
        double detail = endusmroku$valueNoise2D(x * 0.032 - 257.0, z * 0.032 + 139.0);

        double mountainNoise = endusmroku$valueNoise2D(x * 0.0065 - 307.0, z * 0.0065 + 419.0);
        double ridge = 1.0 - Math.abs(mountainNoise);
        double mountainMask = endusmroku$smooth((mountainNoise + 0.15) / 1.15);
        double mountains = ridge * ridge * mountainMask * 11.0;

        // Negative continental values form broad lowlands; positive values
        // allow occasional higher ground. Heights remain deliberately moderate.
        double change = continental * 8.0 + hills * 5.0 + detail * 1.8
                + mountains - 1.5;
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
