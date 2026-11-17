package pl.enderwilds;

import net.minecraft.block.BlockState;
import net.minecraft.world.BlockView;
import net.minecraft.block.RootsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.shape.VoxelShape;

public class MroczneKorzenieBlock extends RootsBlock {

    private static final VoxelShape SHAPE =
            createCuboidShape(3, 0, 3, 13, 8, 13);

    public MroczneKorzenieBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(
            BlockState state,
            BlockView world,
            BlockPos pos,
            ShapeContext context
    ) {
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Enderwilds.CIEMNE_NYLIUM);
    }
}