package pl.enderwilds.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
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
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(FogRenderer.class)
public abstract class EndergroveFogMixin {
    @Unique
    private static final RegistryKey<Biome> ENDERWILDS_ENDERGROVE = RegistryKey.of(
            RegistryKeys.BIOME,
            Identifier.of("enderwilds", "endergrove")
    );

    @Unique
    private boolean enderwilds$inEndergrove;

    @Inject(method = "applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;", at = @At("HEAD"))
    private void enderwilds$checkBiome(
            Camera camera,
            int viewDistance,
            RenderTickCounter renderTickCounter,
            float tickProgress,
            ClientWorld world,
            CallbackInfoReturnable<Vector4f> cir
    ) {
        this.enderwilds$inEndergrove =
                world.getBiome(camera.getBlockPos()).matchesKey(ENDERWILDS_ENDERGROVE);
    }

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
            cir.setReturnValue(new Vector4f(0.30f, 0.23f, 0.36f, original.w));
        }
    }

    @ModifyArgs(
            method = "applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/fog/FogRenderer;applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V"
            )
    )
    private void enderwilds$adjustFogDistance(Args args) {
        if (this.enderwilds$inEndergrove) {
            args.set(3, 80.0f);
            args.set(4, 200.0f);
            args.set(5, 80.0f);
            args.set(6, 200.0f);
        }
    }
}
