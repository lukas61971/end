package pl.endusmroku.block;

import net.minecraft.block.Block;
import net.minecraft.block.TwistingVinesBlock;

public class DarkTwistingVinesBlock extends TwistingVinesBlock {

    public DarkTwistingVinesBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected Block getPlant() {
        return pl.endusmroku.EndusMroku.DARK_TWISTING_VINES_PLANT;
    }
}