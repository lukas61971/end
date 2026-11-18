package pl.enderwilds.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
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
    private static final RegistryKey<net.minecraft.world.biome.Biome> ENDERWILDS$ENDERGROVE =
            RegistryKey.of(RegistryKeys.BIOME, Identifier.of("enderwilds", "endergrove"));

    @Inject(method = "getFogColor", at = @At("RETURN"))
    private void enderwilds$tintHorizonFog(
            Camera camera,
            float tickProgress,
            ClientWorld world,
            int viewDistance,
            float skyDarkness,
            CallbackInfoReturnable<Vector4f> cir
    ) {
        boolean inEndergrove = world.getBiome(camera.getBlockPos()).matchesKey(ENDERWILDS$ENDERGROVE);
        float target = inEndergrove ? 0.78f : 0.0f;

        // Smoothly ease the tint in and out over several rendered frames.
        enderwilds$purpleFogBlend = MathHelper.lerp(0.055f, enderwilds$purpleFogBlend, target);

        if (enderwilds$purpleFogBlend > 0.001f) {
            Vector4f fogColor = cir.getReturnValue();
            float blend = enderwilds$purpleFogBlend;
            fogColor.set(
                    MathHelper.lerp(blend, fogColor.x, 0.14f),
                    MathHelper.lerp(blend, fogColor.y, 0.045f),
                    MathHelper.lerp(blend, fogColor.z, 0.22f),
                    fogColor.w
            );
        }
    }
}
