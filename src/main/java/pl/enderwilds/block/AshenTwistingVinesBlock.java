package pl.enderwilds.block;
import net.minecraft.block.Block;
import net.minecraft.block.TwistingVinesBlock;
public class AshenTwistingVinesBlock extends TwistingVinesBlock {
 public AshenTwistingVinesBlock(Settings settings) { super(settings); }
 @Override protected Block getPlant() { return pl.enderwilds.Enderwilds.ASHEN_TWISTING_VINES_PLANT; }
}
