package pl.endusmroku.worldgen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import pl.endusmroku.EndusMroku;

public final class DarkVinesFeatures {
    public static final Feature<DefaultFeatureConfig> DARK_TWISTING_VINES =
            Registry.register(
                    Registries.FEATURE,
                    Identifier.of(EndusMroku.MOD_ID, "dark_twisting_vines"),
                    new DarkTwistingVinesFeature()
            );

    public static final Feature<DefaultFeatureConfig> DARK_WEEPING_VINES =
            Registry.register(
                    Registries.FEATURE,
                    Identifier.of(EndusMroku.MOD_ID, "dark_weeping_vines"),
                    new DarkWeepingVinesFeature()
            );

    private DarkVinesFeatures() {
    }
}