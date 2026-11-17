package pl.enderwilds.mixin;

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
import pl.enderwilds.worldgen.DarkForestRegion;

@Mixin(TheEndBiomeSource.class)
public abstract class TheEndBiomeSourceMixin {
    @Unique
    private static RegistryEntry<Biome> enderwilds$darkForestBiome;

    @Inject(method = "createVanilla", at = @At("RETURN"))
    private static void enderwilds$loadDarkForest(
            RegistryEntryLookup<Biome> biomeLookup,
            CallbackInfoReturnable<TheEndBiomeSource> cir
    ) {
        RegistryKey<Biome> key = RegistryKey.of(
                RegistryKeys.BIOME,
                Identifier.of("enderwilds", "dark_forest")
        );
        enderwilds$darkForestBiome = biomeLookup.getOrThrow(key);
    }

    @Inject(method = "biomeStream", at = @At("RETURN"), cancellable = true)
    private void enderwilds$includeDarkForest(
            CallbackInfoReturnable<Stream<RegistryEntry<Biome>>> cir
    ) {
        if (enderwilds$darkForestBiome != null) {
            cir.setReturnValue(Stream.concat(cir.getReturnValue(), Stream.of(enderwilds$darkForestBiome)));
        }
    }

    @Inject(method = "getBiome", at = @At("RETURN"), cancellable = true)
    private void enderwilds$selectDarkForest(
            int x,
            int y,
            int z,
            MultiNoiseUtil.MultiNoiseSampler noise,
            CallbackInfoReturnable<RegistryEntry<Biome>> cir
    ) {
        if (enderwilds$darkForestBiome != null && DarkForestRegion.isInside(x * 4.0, z * 4.0)) {
            cir.setReturnValue(enderwilds$darkForestBiome);
        }
    }
}
