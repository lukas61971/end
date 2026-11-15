package pl.endusmroku.mixin;

import java.util.stream.Stream;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.TheEndBiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TheEndBiomeSource.class)
public abstract class TheEndBiomeSourceMixin {
    @Unique
    private static final int endusmroku$PATCH_SPACING = 2000;

    @Unique
    private static RegistryEntry<Biome> endusmroku$darkForestBiome;

    @Inject(method = "createVanilla", at = @At("RETURN"))
    private static void endusmroku$loadDarkForest(
            RegistryEntryLookup<Biome> biomeLookup,
            CallbackInfoReturnable<TheEndBiomeSource> cir
    ) {
        RegistryKey<Biome> key = RegistryKey.of(
                RegistryKeys.BIOME,
                Identifier.of("endusmroku", "dark_forest")
        );
        endusmroku$darkForestBiome = biomeLookup.getOrThrow(key);
    }

    @Inject(method = "biomeStream", at = @At("RETURN"), cancellable = true)
    private void endusmroku$includeDarkForest(
            CallbackInfoReturnable<Stream<RegistryEntry<Biome>>> cir
    ) {
        if (endusmroku$darkForestBiome != null) {
            cir.setReturnValue(Stream.concat(cir.getReturnValue(), Stream.of(endusmroku$darkForestBiome)));
        }
    }

    @Inject(method = "getBiome", at = @At("RETURN"), cancellable = true)
    private void endusmroku$selectDarkForest(
            int x,
            int y,
            int z,
            MultiNoiseUtil.MultiNoiseSampler noise,
            CallbackInfoReturnable<RegistryEntry<Biome>> cir
    ) {
        if (endusmroku$darkForestBiome != null && endusmroku$isInsideDarkForest(x * 4.0, z * 4.0)) {
            cir.setReturnValue(endusmroku$darkForestBiome);
        }
    }

    @Unique
    public static boolean endusmroku$isInsideDarkForest(double x, double z) {
        int cellX = (int) Math.floor(x / ENDURO_SPACING);
        int cellZ = (int) Math.floor(z / ENDURO_SPACING);

        for (int gx = cellX - 1; gx <= cellX + 1; gx++) {
            for (int gz = cellZ - 1; gz <= cellZ + 1; gz++) {
                double seed = endusmroku$hash(gx, gz);
                double centerX = gx * (double) ENDURO_SPACING + endusmroku$hash(gx + 17, gz - 31) * 320.0;
                double centerZ = gz * (double) ENDURO_SPACING + endusmroku$hash(gx - 47, gz + 11) * 320.0;

                // Keep the patches away from the central End island.
                if (centerX * centerX + centerZ * centerZ < 1100.0 * 1100.0) {
                    continue;
                }

                double dx = x - centerX;
                double dz = z - centerZ;
                double distance = Math.sqrt(dx * dx + dz * dz);
                double angle = Math.atan2(dz, dx);
                double radius = 235.0 + seed * 22.0
                        + 42.0 * Math.sin(angle * 2.0 + seed * 4.0)
                        + 27.0 * Math.sin(angle * 5.0 - seed * 7.0)
                        + 17.0 * Math.sin(angle * 9.0 + seed * 11.0)
                        + 11.0 * Math.sin(angle * 13.0 - seed * 3.0);

                if (distance <= radius) {
                    return true;
                }
            }
        }
        return false;
    }

    @Unique
    private static final int ENDURO_SPACING = 2000;

    @Unique
    private static double endusmroku$hash(int x, int z) {
        long n = (long) x * 341873128712L + (long) z * 132897987541L;
        n = (n ^ (n >> 13)) * 1274126177L;
        n ^= n >> 16;
        return ((n & 0x7fffffffL) / 1073741823.5) - 1.0;
    }
}
