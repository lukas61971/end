package pl.enderwilds.block;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.TwistingVinesPlantBlock;
public class AshenTwistingVinesPlantBlock extends TwistingVinesPlantBlock {
 public AshenTwistingVinesPlantBlock(Settings settings) { super(settings); }
 @Override protected AbstractPlantStemBlock getStem() { return pl.enderwilds.Enderwilds.ASHEN_TWISTING_VINES; }
}
