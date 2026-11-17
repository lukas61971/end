package pl.endusmroku.block;

import net.minecraft.block.Block;
import net.minecraft.block.TwistingVinesBlock;

public class EnderTwistingVinesBlock extends TwistingVinesBlock {

    public EnderTwistingVinesBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected Block getPlant() {
        return pl.endusmroku.EndusMroku.ENDER_TWISTING_VINES_PLANT;
    }
}