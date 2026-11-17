package pl.endusmroku.block;

import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.WeepingVinesPlantBlock;

public class EnderWeepingVinesPlantBlock extends WeepingVinesPlantBlock {

    public EnderWeepingVinesPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return pl.endusmroku.EndusMroku.ENDER_WEEPING_VINES;
    }
}