package pl.endusmroku;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class DarkSproutsBlock extends PlantBlock {

    public static final MapCodec<DarkSproutsBlock> CODEC =
            createCodec(DarkSproutsBlock::new);

    private static final VoxelShape SHAPE =
            createCuboidShape(2, 0, 2, 14, 3, 14);

    public DarkSproutsBlock(Settings settings) {
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
        return floor.isOf(EndusMroku.CIEMNE_NYLIUM);
    }
}