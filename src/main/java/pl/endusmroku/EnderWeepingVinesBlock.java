package pl.endusmroku.block;

import net.minecraft.block.Block;
import net.minecraft.block.WeepingVinesBlock;

public class EnderWeepingVinesBlock extends WeepingVinesBlock {

    public EnderWeepingVinesBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected Block getPlant() {
        return pl.endusmroku.EndusMroku.ENDER_WEEPING_VINES_PLANT;
    }
}