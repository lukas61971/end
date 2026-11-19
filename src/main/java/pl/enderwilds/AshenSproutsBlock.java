package pl.enderwilds;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class AshenSproutsBlock extends PlantBlock {

    public static final MapCodec<AshenSproutsBlock> CODEC =
            createCodec(AshenSproutsBlock::new);

    private static final VoxelShape SHAPE =
            createCuboidShape(2, 0, 2, 14, 3, 14);

    public AshenSproutsBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
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
        return floor.isOf(Enderwilds.ASHEN_NYLIUM);
    }
}