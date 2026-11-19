package pl.enderwilds.block;
import net.minecraft.block.Block;
import net.minecraft.block.WeepingVinesBlock;
public class AshenWeepingVinesBlock extends WeepingVinesBlock {
 public AshenWeepingVinesBlock(Settings settings) { super(settings); }
 @Override protected Block getPlant() { return pl.enderwilds.Enderwilds.ASHEN_WEEPING_VINES_PLANT; }
}
