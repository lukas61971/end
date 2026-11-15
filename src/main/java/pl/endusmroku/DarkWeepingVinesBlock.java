package pl.endusmroku.block;

import net.minecraft.block.Block;
import net.minecraft.block.WeepingVinesBlock;

public class DarkWeepingVinesBlock extends WeepingVinesBlock {

    public DarkWeepingVinesBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected Block getPlant() {
        return pl.endusmroku.EndusMroku.DARK_WEEPING_VINES_PLANT;
    }
}