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
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class EndusMroku implements ModInitializer {
    public static final String MOD_ID = "endusmroku";

    private static final Identifier CIEMNE_NYLIUM_ID =
            Identifier.of(MOD_ID, "ciemne_nylium");

    public static final RegistryKey<Block> CIEMNE_NYLIUM_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, CIEMNE_NYLIUM_ID);

    public static final RegistryKey<Item> CIEMNE_NYLIUM_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, CIEMNE_NYLIUM_ID);

    public static final Block CIEMNE_NYLIUM = Registry.register(
            Registries.BLOCK,
            CIEMNE_NYLIUM_ID,
            new Block(AbstractBlock.Settings.copy(Blocks.WARPED_NYLIUM)
                    .registryKey(CIEMNE_NYLIUM_KEY)
                    .mapColor(MapColor.PURPLE))
    );

    public static final Item CIEMNE_NYLIUM_ITEM = Registry.register(
            Registries.ITEM,
            CIEMNE_NYLIUM_ID,
            new BlockItem(CIEMNE_NYLIUM, new Item.Settings()
                    .registryKey(CIEMNE_NYLIUM_ITEM_KEY))
    );

    @Override
    public void onInitialize() {
        // Blok i przedmiot są rejestrowane podczas inicjalizacji klasy.
    }
}
