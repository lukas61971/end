
package pl.enderwilds;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Optional;

public class EnderFungusBlock extends PlantBlock implements Fertilizable {

    public static final MapCodec<EnderFungusBlock> CODEC =
            createCodec(EnderFungusBlock::new);

    private static final VoxelShape SHAPE =
            createCuboidShape(4, 0, 4, 12, 8, 12);

    public EnderFungusBlock(Settings settings) {
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
    protected boolean canPlantOnTop(
            BlockState floor,
            BlockView world,
            BlockPos pos
    ) {
        return floor.isOf(Enderwilds.ENDER_NYLIUM);
    }

    // MĄCZKA KOSTNA

    @Override
    public boolean isFertilizable(
            WorldView world,
            BlockPos pos,
            BlockState state
    ) {
        return world.getBlockState(pos.down())
                .isOf(Enderwilds.ENDER_NYLIUM);
    }

    @Override
    public boolean canGrow(
            World world,
            Random random,
            BlockPos pos,
            BlockState state
    ) {
        return true;
    }

    @Override
    public void grow(
            ServerWorld world,
            Random random,
            BlockPos pos,
            BlockState state
    ) {
        // 40% SZANSY NA WYROŚNIĘCIE

        if (random.nextFloat() < 0.4F) {
            growHugeFungus(world, random, pos);
        }
    }

    // GENEROWANIE WIELKIEGO MROCZNEGO GRZYBA

    private void growHugeFungus(
            ServerWorld world,
            Random random,
            BlockPos pos
    ) {

        HugeFungusFeatureConfig config =
                new HugeFungusFeatureConfig(
                        Enderwilds.ENDER_NYLIUM.getDefaultState(),
                        Enderwilds.ENDER_STEM.getDefaultState(),
                        Enderwilds.ENDER_WART_BLOCK.getDefaultState(),
                        Blocks.AIR.getDefaultState(),
                        BlockPredicate.replaceable(),
                        true
                );

        FeatureContext<HugeFungusFeatureConfig> context =
                new FeatureContext<>(
                        Optional.empty(),
                        world,
                        world.getChunkManager().getChunkGenerator(),
                        random,
                        pos,
                        config
                );

        Feature.HUGE_FUNGUS.generate(context);
    }
}
