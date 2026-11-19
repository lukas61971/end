package pl.enderwilds.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class AshenwoodColorGradeMixin {
    @Unique
    private static final RegistryKey<Biome> ENDERWILDS$ASHENWOOD =
            RegistryKey.of(RegistryKeys.BIOME, Identifier.of("enderwilds", "ashenwood"));

    @Unique
    private float enderwilds$grayscaleBlend = 0.0f;

    @Shadow @Final private MinecraftClient client;
    @Shadow public abstract Camera getCamera();
    @Shadow private void setPostProcessor(Identifier id) {}
    @Shadow public abstract void clearPostProcessor();
    @Shadow public abstract Identifier getPostProcessorId();

    @Inject(method = "renderWorld", at = @At("HEAD"))
    private void enderwilds$updateAshenwoodColorGrade(
            RenderTickCounter tickCounter,
            CallbackInfo ci
    ) {
        boolean inAshenwood = client.world != null
                && client.world.getBiome(getCamera().getBlockPos()).matchesKey(ENDERWILDS$ASHENWOOD);

        float target = inAshenwood ? 1.0f : 0.0f;
        enderwilds$grayscaleBlend = MathHelper.lerp(
                0.10f, enderwilds$grayscaleBlend, target
        );
        if (Math.abs(enderwilds$grayscaleBlend - target) < 0.01f) {
            enderwilds$grayscaleBlend = target;
        }

        int stage = Math.round(enderwilds$grayscaleBlend * 10.0f);
        Identifier current = getPostProcessorId();
        boolean currentIsOurs = current != null
                && current.getNamespace().equals("enderwilds")
                && current.getPath().startsWith("ashenwood_grayscale_");

        if (stage > 0) {
            Identifier wanted = Identifier.of("enderwilds", "ashenwood_grayscale_" + stage);
            if (!wanted.equals(current)) {
                setPostProcessor(wanted);
            }
        } else if (currentIsOurs) {
            clearPostProcessor();
        }
    }
}
