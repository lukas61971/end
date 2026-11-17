package pl.enderwilds.worldgen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import pl.enderwilds.Enderwilds;

public final class EnderVinesFeatures {
    public static final Feature<DefaultFeatureConfig> ENDER_TWISTING_VINES =
            Registry.register(
                    Registries.FEATURE,
                    Identifier.of(Enderwilds.MOD_ID, "ender_twisting_vines"),
                    new EnderTwistingVinesFeature()
            );

    public static final Feature<DefaultFeatureConfig> ENDER_WEEPING_VINES =
            Registry.register(
                    Registries.FEATURE,
                    Identifier.of(Enderwilds.MOD_ID, "ender_weeping_vines"),
                    new EnderWeepingVinesFeature()
            );

    public static void register() {
        // Calling this method initializes and registers both feature entries.
    }

    private EnderVinesFeatures() {
    }
}