package pl.endusmroku;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CiemneNyliumBlock extends Block implements Fertilizable {

    public static final MapCodec<CiemneNyliumBlock> CODEC =
            createCodec(CiemneNyliumBlock::new);

    public CiemneNyliumBlock(Settings settings) {
        super(settings);
    }

    // PRZEMIANA W END STONE

    @Override
    protected void randomTick(
            BlockState state,
            ServerWorld world,
            BlockPos pos,
            Random random
    ) {
        BlockPos abovePos = pos.up();
        BlockState above = world.getBlockState(abovePos);

        if (above.isOpaqueFullCube()) {
            world.setBlockState(pos, net.minecraft.block.Blocks.END_STONE.getDefaultState());
        }
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    // MĄCZKA KOSTNA

    @Override
    public boolean isFertilizable(
            WorldView world,
            BlockPos pos,
            BlockState state
    ) {
        return world.getBlockState(pos.up()).isAir();
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
        for (int i = 0; i < 9; i++) {

            int x = random.nextInt(3) + random.nextInt(3) - 2;
            int z = random.nextInt(3) + random.nextInt(3) - 2;

            BlockPos target = pos.add(x, 1, z);
            BlockPos top = target.up();

            if (!world.getBlockState(target).isAir()) {
                continue;
            }

            if (!world.getBlockState(target.down()).isOf(this)) {
                continue;
            }

            int choice = random.nextInt(23);

            Block plant;

            if (choice <= 1) {
                plant = EndusMroku.ENDER_FUNGUS;

            } else if (choice <= 11) {
                plant = EndusMroku.MROCZNE_KORZENIE;

            } else if (choice <= 21) {
                plant = EndusMroku.ENDER_SPROUTS;

            } else {
                // Pnącze wymaga dwóch wolnych bloków
                if (!world.getBlockState(top).isAir()) {
                    continue;
                }

                // Dolna część pnącza
                world.setBlockState(
                        target,
                        EndusMroku.ENDER_TWISTING_VINES_PLANT.getDefaultState()
                );

                // Górna część pnącza
                world.setBlockState(
                        top,
                        EndusMroku.ENDER_TWISTING_VINES.getDefaultState()
                );

                continue;
            }

            world.setBlockState(target, plant.getDefaultState());
        }
    }
}