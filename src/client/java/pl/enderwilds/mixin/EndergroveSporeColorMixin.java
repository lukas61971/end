package pl.enderwilds.mixin;

import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.WaterSuspendParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterSuspendParticle.CrimsonSporeFactory.class)
public abstract class EndergroveSporeColorMixin {
    private static final RegistryKey<Biome> ENDERGROVE =
            RegistryKey.of(RegistryKeys.BIOME, Identifier.of("enderwilds", "endergrove"));
    private static final RegistryKey<Biome> ASHENWOOD =
            RegistryKey.of(RegistryKeys.BIOME, Identifier.of("enderwilds", "ashenwood"));

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
        var biome = world.getBiome(BlockPos.ofFloored(x, y, z));
        BillboardParticle particle = (BillboardParticle) cir.getReturnValue();

        if (biome.matchesKey(ASHENWOOD)) {
            particle.setColor(0.78f, 0.78f, 0.80f);
        } else if (biome.matchesKey(ENDERGROVE)) {
            particle.setColor(0.58f, 0.22f, 0.88f);
        }
    }
}
