package pl.enderwilds.block;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.WeepingVinesPlantBlock;
public class AshenWeepingVinesPlantBlock extends WeepingVinesPlantBlock {
 public AshenWeepingVinesPlantBlock(Settings settings) { super(settings); }
 @Override protected AbstractPlantStemBlock getStem() { return pl.enderwilds.Enderwilds.ASHEN_WEEPING_VINES; }
}
