package pl.enderwilds.block;

import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.WeepingVinesPlantBlock;

public class EnderWeepingVinesPlantBlock extends WeepingVinesPlantBlock {

    public EnderWeepingVinesPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return pl.enderwilds.Enderwilds.ENDER_WEEPING_VINES;
    }
}