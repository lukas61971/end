package pl.enderwilds.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import pl.enderwilds.Enderwilds;

public class EnderWeepingVinesFeature extends Feature<DefaultFeatureConfig> {
    public EnderWeepingVinesFeature() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        var random = context.getRandom();
        int minY = world.getBottomY() + 12;
        int maxY = world.getTopYInclusive() - 8;
        if (maxY <= minY) {
            return false;
        }

        boolean placedAny = false;
        BlockPos origin = context.getOrigin();

        // Search multiple positions in this chunk for real underground ceilings.
        for (int attempt = 0; attempt < 48; attempt++) {
            int chunkX = origin.getX() & ~15;
            int chunkZ = origin.getZ() & ~15;
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = minY + random.nextInt(maxY - minY);
            BlockPos ceilingPos = new BlockPos(x, y, z);
            BlockPos topVinePos = ceilingPos.down();

            if (!world.getBlockState(ceilingPos).isSolidBlock(world, ceilingPos)) {
                continue;
            }
            if (!world.getBlockState(ceilingPos.up()).isSolidBlock(world, ceilingPos.up())) {
                continue;
            }

            int length = 2 + random.nextInt(5);
            boolean clear = true;
            for (int i = 0; i < length; i++) {
                if (!world.getBlockState(topVinePos.down(i)).isAir()) {
                    clear = false;
                    break;
                }
            }
            if (!clear) {
                continue;
            }

            BlockState head = Enderwilds.ENDER_WEEPING_VINES.getDefaultState();
            BlockState plant = Enderwilds.ENDER_WEEPING_VINES_PLANT.getDefaultState();
            if (!head.canPlaceAt(world, topVinePos)) {
                continue;
            }

            world.setBlockState(topVinePos, head, Block.NOTIFY_LISTENERS);
            for (int i = 1; i < length; i++) {
                world.setBlockState(topVinePos.down(i), plant, Block.NOTIFY_LISTENERS);
            }
            placedAny = true;
        }

        return placedAny;
    }
}