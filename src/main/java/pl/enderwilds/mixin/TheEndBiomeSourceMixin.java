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
import pl.enderwilds.worldgen.EndergroveRegion;
import pl.enderwilds.worldgen.AshenwoodRegion;

@Mixin(TheEndBiomeSource.class)
public abstract class TheEndBiomeSourceMixin {
    @Unique
    private static RegistryEntry<Biome> enderwilds$enderGroveBiome;
    @Unique
    private static RegistryEntry<Biome> enderwilds$ashenwoodBiome;

    @Inject(method = "createVanilla", at = @At("RETURN"))
    private static void enderwilds$loadEndergrove(
            RegistryEntryLookup<Biome> biomeLookup,
            CallbackInfoReturnable<TheEndBiomeSource> cir
    ) {
        RegistryKey<Biome> key = RegistryKey.of(
                RegistryKeys.BIOME,
                Identifier.of("enderwilds", "endergrove")
        );
        enderwilds$enderGroveBiome = biomeLookup.getOrThrow(key);
        RegistryKey<Biome> ashenKey = RegistryKey.of(RegistryKeys.BIOME, Identifier.of("enderwilds", "ashenwood"));
        enderwilds$ashenwoodBiome = biomeLookup.getOrThrow(ashenKey);
    }

    @Inject(method = "biomeStream", at = @At("RETURN"), cancellable = true)
    private void enderwilds$includeEndergrove(
            CallbackInfoReturnable<Stream<RegistryEntry<Biome>>> cir
    ) {
        if (enderwilds$enderGroveBiome != null) {
            cir.setReturnValue(Stream.concat(cir.getReturnValue(), Stream.of(enderwilds$enderGroveBiome, enderwilds$ashenwoodBiome)));
        }
    }

    @Inject(method = "getBiome", at = @At("RETURN"), cancellable = true)
    private void enderwilds$selectEndergrove(
            int x,
            int y,
            int z,
            MultiNoiseUtil.MultiNoiseSampler noise,
            CallbackInfoReturnable<RegistryEntry<Biome>> cir
    ) {
        if (enderwilds$enderGroveBiome != null && EndergroveRegion.isInside(x * 4.0, z * 4.0)) {
            cir.setReturnValue(enderwilds$enderGroveBiome);
        } else if (enderwilds$ashenwoodBiome != null && AshenwoodRegion.isInside(x * 4.0, z * 4.0)) {
            cir.setReturnValue(enderwilds$ashenwoodBiome);
        }
    }
}
