package pl.endusmroku.block;

import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.TwistingVinesPlantBlock;

public class DarkTwistingVinesPlantBlock extends TwistingVinesPlantBlock {

    public DarkTwistingVinesPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return pl.endusmroku.EndusMroku.DARK_TWISTING_VINES;
    }
}