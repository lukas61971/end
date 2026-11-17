
package pl.endusmroku;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.PillarBlock;

public class EnderStemBlock extends PillarBlock {

    public static final MapCodec<EnderStemBlock> CODEC =
            createCodec(EnderStemBlock::new);

    public EnderStemBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends PillarBlock> getCodec() {
        return CODEC;
    }
}
