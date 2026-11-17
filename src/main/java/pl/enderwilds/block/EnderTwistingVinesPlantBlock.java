package pl.enderwilds.block;

import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.TwistingVinesPlantBlock;

public class EnderTwistingVinesPlantBlock extends TwistingVinesPlantBlock {

    public EnderTwistingVinesPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return pl.enderwilds.Enderwilds.ENDER_TWISTING_VINES;
    }
}