package pl.endusmroku.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import pl.endusmroku.EndusMroku;

public class DarkTwistingVinesFeature extends Feature<DefaultFeatureConfig> {
    public DarkTwistingVinesFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos base = context.getOrigin();
        var random = context.getRandom();

        if (!world.getBlockState(base.down()).isOf(EndusMroku.CIEMNE_NYLIUM)) {
            return false;
        }

        int height = 1 + random.nextInt(5);
        BlockState plant = EndusMroku.DARK_TWISTING_VINES_PLANT.getDefaultState();
        BlockState head = EndusMroku.DARK_TWISTING_VINES.getDefaultState();

        for (int i = 0; i < height; i++) {
            BlockPos pos = base.up(i);
            if (!world.getBlockState(pos).isAir()) {
                return false;
            }
        }

        if (!plant.canPlaceAt(world, base)) {
            return false;
        }

        for (int i = 0; i < height - 1; i++) {
            world.setBlockState(base.up(i), plant, Block.NOTIFY_LISTENERS);
        }

        BlockPos headPos = base.up(height - 1);
        if (height == 1) {
            // A one-block vine uses its head block; the nylium below is its support.
            return world.setBlockState(headPos, head, Block.NOTIFY_LISTENERS);
        }

        if (!head.canPlaceAt(world, headPos)) {
            return false;
        }
        return world.setBlockState(headPos, head, Block.NOTIFY_LISTENERS);
    }
}