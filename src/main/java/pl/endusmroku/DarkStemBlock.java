
package pl.endusmroku;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.PillarBlock;

public class DarkStemBlock extends PillarBlock {

    public static final MapCodec<DarkStemBlock> CODEC =
            createCodec(DarkStemBlock::new);

    public DarkStemBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends PillarBlock> getCodec() {
        return CODEC;
    }
}
