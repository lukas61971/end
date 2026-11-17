package pl.enderwilds.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.Heightmap;
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
        BlockPos origin = context.getOrigin();
        int minY = world.getBottomY() + 12;

        int chunkX = origin.getX() & ~15;
        int chunkZ = origin.getZ() & ~15;

        // Search enough positions in this chunk, using each column's surface
        // as an upper limit so attempts are concentrated below ground.
        for (int attempt = 0; attempt < 256; attempt++) {
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int surfaceY = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);
            int maxY = surfaceY - 5;
            if (maxY <= minY) {
                continue;
            }

            int y = minY + random.nextInt(maxY - minY + 1);
            BlockPos ceilingPos = new BlockPos(x, y, z);
            if (!world.getBlockState(ceilingPos).isSolidBlock(world, ceilingPos)) {
                continue;
            }

            BlockPos topVinePos = ceilingPos.down();

            // Require a cave-like space: a ceiling above and solid ground
            // reasonably close below. Floating islands can block sky visibility,
            // so sky visibility alone does not distinguish them from caves.
            if (world.isSkyVisible(topVinePos)) {
                continue;
            }

            int length = 1 + random.nextInt(4);
            BlockPos bottomVinePos = topVinePos.down(length - 1);

            boolean hasFloor = false;
            for (int distance = 1; distance <= 16; distance++) {
                BlockPos below = bottomVinePos.down(distance);
                if (world.getBlockState(below).isSolidBlock(world, below)) {
                    hasFloor = true;
                    break;
                }
            }
            if (!hasFloor) {
                continue;
            }
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
            // Match the proven placement used by the fungus-cap vine mixin:
            // plant segments above, the vine head at the bottom.
            for (int i = 0; i < length - 1; i++) {
                world.setBlockState(topVinePos.down(i), plant, Block.NOTIFY_ALL);
            }
            world.setBlockState(bottomVinePos, head, Block.NOTIFY_ALL);
            return true;
        }

        return false;
    }
}
