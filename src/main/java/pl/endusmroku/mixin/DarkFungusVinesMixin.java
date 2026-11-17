package pl.endusmroku.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.endusmroku.EndusMroku;

@Mixin(targets = "net.minecraft.world.gen.feature.HugeFungusFeature")
public abstract class DarkFungusVinesMixin {
    @Inject(
            method = "generateHat(Lnet/minecraft/world/StructureWorldAccess;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/gen/feature/HugeFungusFeatureConfig;Lnet/minecraft/util/math/BlockPos;IZ)V",
            at = @At("RETURN")
    )
    private void endusmroku$addVinesToDarkFungusHat(
            StructureWorldAccess world,
            Random random,
            HugeFungusFeatureConfig config,
            BlockPos origin,
            int hatHeight,
            boolean thickStem,
            CallbackInfo ci
    ) {
        if (!config.hatState.isOf(EndusMroku.DARK_WART_BLOCK)) {
            return;
        }

        BlockPos.Mutable capPos = new BlockPos.Mutable();
        int minY = Math.max(world.getBottomY(), origin.getY() - 2);
        int maxY = Math.min(world.getBottomY() + world.getHeight() - 1, origin.getY() + hatHeight + 3);

        for (int x = origin.getX() - 5; x <= origin.getX() + 5; x++) {
            for (int z = origin.getZ() - 5; z <= origin.getZ() + 5; z++) {
                for (int y = minY; y <= maxY; y++) {
                    capPos.set(x, y, z);
                    if (!world.getBlockState(capPos).isOf(EndusMroku.DARK_WART_BLOCK)) continue;
                    if (!world.getBlockState(capPos.up()).isOf(EndusMroku.DARK_WART_BLOCK)
                            || !world.getBlockState(capPos.down()).isAir()) continue;
                    if (random.nextInt(5) != 0) continue;

                    int length = random.nextInt(3) + 2;
                    for (int i = 1; i <= length; i++) {
                        BlockPos vinePos = capPos.down(i);
                        if (!world.getBlockState(vinePos).isAir()) break;
                        BlockState vineState = i == length
                                ? EndusMroku.DARK_WEEPING_VINES.getDefaultState()
                                : EndusMroku.DARK_WEEPING_VINES_PLANT.getDefaultState();
                        world.setBlockState(vinePos, vineState, Block.NOTIFY_ALL);
                    }
                }
            }
        }
    }
}
