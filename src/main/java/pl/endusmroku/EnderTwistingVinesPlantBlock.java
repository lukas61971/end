package pl.endusmroku.block;

import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.TwistingVinesPlantBlock;

public class EnderTwistingVinesPlantBlock extends TwistingVinesPlantBlock {

    public EnderTwistingVinesPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return pl.endusmroku.EndusMroku.ENDER_TWISTING_VINES;
    }
}