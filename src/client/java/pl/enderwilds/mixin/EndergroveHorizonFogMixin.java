package pl.enderwilds.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public abstract class EndergroveHorizonFogMixin {
    @Unique
    private static float enderwilds$purpleFogBlend = 0.0f;

    @Unique
    private static float enderwilds$grayFogBlend = 0.0f;

    @Unique
    private static final RegistryKey<net.minecraft.world.biome.Biome> ENDERWILDS$ENDERGROVE =
            RegistryKey.of(RegistryKeys.BIOME, Identifier.of("enderwilds", "endergrove"));
    @Unique
    private static final RegistryKey<net.minecraft.world.biome.Biome> ENDERWILDS$ASHENWOOD =
            RegistryKey.of(RegistryKeys.BIOME, Identifier.of("enderwilds", "ashenwood"));

    @Inject(method = "getFogColor", at = @At("RETURN"))
    private void enderwilds$tintHorizonFog(
            Camera camera,
            float tickProgress,
            ClientWorld world,
            int viewDistance,
            float skyDarkness,
            CallbackInfoReturnable<Vector4f> cir
    ) {
        var biome = world.getBiome(camera.getBlockPos());
        boolean inEndergrove = biome.matchesKey(ENDERWILDS$ENDERGROVE);
        boolean inAshenwood = biome.matchesKey(ENDERWILDS$ASHENWOOD);

        // Keep the original purple Endergrove tint, but give Ashenwood a neutral gray tint.
        enderwilds$purpleFogBlend = MathHelper.lerp(0.055f, enderwilds$purpleFogBlend,
                inEndergrove ? 0.78f : 0.0f);
        enderwilds$grayFogBlend = MathHelper.lerp(0.055f, enderwilds$grayFogBlend,
                inAshenwood ? 0.78f : 0.0f);

        Vector4f fogColor = cir.getReturnValue();
        if (enderwilds$purpleFogBlend > 0.001f) {
            float blend = enderwilds$purpleFogBlend;
            fogColor.set(
                    MathHelper.lerp(blend, fogColor.x, 0.14f),
                    MathHelper.lerp(blend, fogColor.y, 0.045f),
                    MathHelper.lerp(blend, fogColor.z, 0.22f),
                    fogColor.w
            );
        }
        if (enderwilds$grayFogBlend > 0.001f) {
            float blend = enderwilds$grayFogBlend;
            fogColor.set(
                    MathHelper.lerp(blend, fogColor.x, 0.32f),
                    MathHelper.lerp(blend, fogColor.y, 0.32f),
                    MathHelper.lerp(blend, fogColor.z, 0.34f),
                    fogColor.w
            );
        }
    }
}
