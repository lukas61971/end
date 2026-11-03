package pl.endusmroku;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EndusMroku implements ModInitializer {
    public static final String MOD_ID = "endusmroku";

    public static final Block CIEMNE_NYLIUM = Registry.register(
            Registries.BLOCK,
            Identifier.of(MOD_ID, "ciemne_nylium"),
            new Block(AbstractBlock.Settings.copy(Blocks.WARPED_NYLIUM)
                    .mapColor(MapColor.DARK_PURPLE))
    );

    public static final Item CIEMNE_NYLIUM_ITEM = Registry.register(
            Registries.ITEM,
            Identifier.of(MOD_ID, "ciemne_nylium"),
            new BlockItem(CIEMNE_NYLIUM, new Item.Settings())
    );

    @Override
    public void onInitialize() {
        // Rejestracja bloku i przedmiotu odbywa się przy inicjalizacji klasy.
    }
}
