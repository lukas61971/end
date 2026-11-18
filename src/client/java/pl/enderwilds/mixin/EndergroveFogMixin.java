package pl.enderwilds.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public abstract class EndergroveFogMixin {
    @Unique
    private static final RegistryKey<Biome> ENDERWILDS_ENDERGROVE = RegistryKey.of(
            RegistryKeys.BIOME,
            Identifier.of("enderwilds", "endergrove")
    );

    @Inject(method = "getFogColor", at = @At("RETURN"), cancellable = true)
    private void enderwilds$setEndergroveFog(
            Camera camera,
            float tickProgress,
            ClientWorld world,
            int viewDistance,
            float skyDarkness,
            CallbackInfoReturnable<Vector4f> cir
    ) {
        if (world.getBiome(camera.getBlockPos()).matchesKey(ENDERWILDS_ENDERGROVE)) {
            Vector4f original = cir.getReturnValue();
            cir.setReturnValue(new Vector4f(0.31f, 0.14f, 0.40f, original.w));
        }
    }
}
