package pl.endusmroku.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.endusmroku.EndusMroku;

@Mixin(targets = "net.minecraft.world.gen.feature.HugeFungusFeature")
public abstract class DarkFungusVinesMixin {
    @Inject(
            method = "placeWithOptionalVines(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/gen/feature/HugeFungusFeature;generateVines(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/random/Random;)V"
            ),
            cancellable = true
    )
    private void endusmroku$useDarkVinesForDarkFungus(
            WorldAccess world,
            Random random,
            BlockPos pos,
            BlockState state,
            boolean vines,
            CallbackInfo ci
    ) {
        if (!state.isOf(EndusMroku.DARK_WART_BLOCK)) {
            return;
        }

        int length = random.nextInt(3) + 2;

        for (int i = 1; i <= length; i++) {
            BlockPos vinePos = pos.down(i);
            if (!world.getBlockState(vinePos).isAir()) {
                break;
            }

            BlockState vineState = i == length
                    ? EndusMroku.DARK_WEEPING_VINES.getDefaultState()
                    : EndusMroku.DARK_WEEPING_VINES_PLANT.getDefaultState();

            world.setBlockState(vinePos, vineState, Block.NOTIFY_ALL);
        }

        // The dark fungus now places its own custom vines instead of vanilla vines.
        ci.cancel();
    }
}
