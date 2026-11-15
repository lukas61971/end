package pl.endusmroku.block;

import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.WeepingVinesPlantBlock;

public class DarkWeepingVinesPlantBlock extends WeepingVinesPlantBlock {

    public DarkWeepingVinesPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return pl.endusmroku.EndusMroku.DARK_WEEPING_VINES;
    }
}