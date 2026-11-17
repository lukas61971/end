package pl.enderwilds.block;

import net.minecraft.block.Block;
import net.minecraft.block.WeepingVinesBlock;

public class EnderWeepingVinesBlock extends WeepingVinesBlock {

    public EnderWeepingVinesBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected Block getPlant() {
        return pl.enderwilds.Enderwilds.ENDER_WEEPING_VINES_PLANT;
    }
}