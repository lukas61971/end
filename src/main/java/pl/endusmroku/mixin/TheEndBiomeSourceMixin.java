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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TheEndBiomeSource.class)
public abstract class TheEndBiomeSourceMixin {
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
            cir.setReturnValue(Stream.concat(
                    cir.getReturnValue(),
                    Stream.of(endusmroku$darkForestBiome)
            ));
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
        if (endusmroku$darkForestBiome != null && endusmroku$isInsideDarkForest(x, z)) {
            cir.setReturnValue(endusmroku$darkForestBiome);
        }
    }

    @Unique
    private static boolean endusmroku$isInsideDarkForest(int biomeX, int biomeZ) {
        // Biome source coordinates are in quart units (4 blocks).
        double blockX = biomeX * 4.0;
        double blockZ = biomeZ * 4.0;

        // The first test area is on the outer End islands, away from the central island.
        double dx = blockX - 1500.0;
        double dz = blockZ;
        double angle = Math.atan2(dz, dx);

        // A gently irregular boundary, approximately 500 blocks across.
        double radius = 250.0
                + 14.0 * Math.sin(angle * 3.0 + 0.4)
                + 9.0 * Math.sin(angle * 7.0 - 1.2)
                + 6.0 * Math.sin(angle * 11.0 + 0.9);

        return dx * dx + dz * dz <= radius * radius;
    }
}
