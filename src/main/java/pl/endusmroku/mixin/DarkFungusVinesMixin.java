package pl.endusmroku.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
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
        // Only decorate our own fungus trees.
        if (!config.hatState.isOf(EndusMroku.DARK_WART_BLOCK)) {
            return;
        }

        BlockPos.Mutable capPos = new BlockPos.Mutable();
        int minY = Math.max(world.getBottomY(), origin.getY() - 2);
        int worldMaxY = world.getBottomY() + world.getHeight() - 1;
        int maxY = Math.min(worldMaxY, origin.getY() + hatHeight + 3);

        // Huge fungus caps are small; this bounded scan stays local to the tree.
        for (int x = origin.getX() - 5; x <= origin.getX() + 5; x++) {
            for (int z = origin.getZ() - 5; z <= origin.getZ() + 5; z++) {
                for (int y = minY; y <= maxY; y++) {
                    capPos.set(x, y, z);

                    if (!world.getBlockState(capPos).isOf(EndusMroku.DARK_WART_BLOCK)) {
                        continue;
                    }

                    // A block with another cap block above and air below is on the underside.
                    if (!world.getBlockState(capPos.up()).isOf(EndusMroku.DARK_WART_BLOCK)
                            || !world.getBlockState(capPos.down()).isAir()) {
                        continue;
                    }

                    // A few hanging strands per tree, rather than vines under every cap block.
                    if (random.nextInt(5) != 0) {
                        continue;
                    }

                    int length = random.nextInt(3) + 2;
                    for (int i = 1; i <= length; i++) {
                        BlockPos vinePos = capPos.down(i);
                        if (!world.getBlockState(vinePos).isAir()) {
                            break;
                        }

                        BlockState vineState = i == length
                                ? EndusMroku.DARK_WEEPING_VINES.getDefaultState()
                                : EndusMroku.DARK_WEEPING_VINES_PLANT.getDefaultState();

                        world.setBlockState(vinePos, vineState, Block.NOTIFY_ALL);
                    }
                }
            }
        }
    }

    @Inject(
            method = "generate(Lnet/minecraft/world/gen/feature/util/FeatureContext;)Z",
            at = @At("RETURN")
    )
    private void endusmroku$limitDarkFungusHeight(
            FeatureContext<HugeFungusFeatureConfig> context,
            CallbackInfoReturnable<Boolean> cir
    ) {
        HugeFungusFeatureConfig config = context.getConfig();
        if (!config.hatState.isOf(EndusMroku.DARK_WART_BLOCK)) {
            return;
        }

        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        int firstTooHighY = origin.getY() + 17;
        int maxY = Math.min(world.getBottomY() + world.getHeight() - 1, origin.getY() + 40);
        BlockPos.Mutable pos = new BlockPos.Mutable();

        // The base block is counted as block 1, so blocks at baseY + 17 and above are removed.
        for (int x = origin.getX() - 8; x <= origin.getX() + 8; x++) {
            for (int z = origin.getZ() - 8; z <= origin.getZ() + 8; z++) {
                for (int y = firstTooHighY; y <= maxY; y++) {
                    pos.set(x, y, z);
                    BlockState state = world.getBlockState(pos);
                    if (state.isOf(EndusMroku.DARK_STEM)
                            || state.isOf(EndusMroku.DARK_WART_BLOCK)
                            || state.isOf(EndusMroku.DARK_WEEPING_VINES)
                            || state.isOf(EndusMroku.DARK_WEEPING_VINES_PLANT)) {
                        world.setBlockState(pos, net.minecraft.block.Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                    }
                }
            }
        }
    }
}
