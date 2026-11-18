package pl.enderwilds.mixin;

import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.WaterSuspendParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterSuspendParticle.CrimsonSporeFactory.class)
public abstract class EndergroveSporeColorMixin {
    @Inject(method = "createParticle", at = @At("RETURN"))
    private void enderwilds$recolorEndergroveSpores(
            SimpleParticleType type,
            ClientWorld world,
            double x,
            double y,
            double z,
            double velocityX,
            double velocityY,
            double velocityZ,
            Random random,
            CallbackInfoReturnable<Particle> cir
    ) {
        if (world.getBiome(BlockPos.ofFloored(x, y, z))
                .matchesKey(net.minecraft.registry.RegistryKey.of(
                        net.minecraft.registry.RegistryKeys.BIOME,
                        net.minecraft.util.Identifier.of("enderwilds", "endergrove")
                ))) {
            ((BillboardParticle) cir.getReturnValue()).setColor(0.58f, 0.22f, 0.88f);
        }
    }
}
