
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
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.SignItem;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.item.HangingSignItem;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import pl.endusmroku.block.DarkTwistingVinesBlock;
import pl.endusmroku.block.DarkTwistingVinesPlantBlock;
import pl.endusmroku.block.DarkTwistingVinesBlock;
import pl.endusmroku.block.DarkTwistingVinesPlantBlock; 
import pl.endusmroku.block.DarkWeepingVinesBlock;
import pl.endusmroku.block.DarkWeepingVinesPlantBlock;

public class EndusMroku implements ModInitializer {
    public static final String MOD_ID = "endusmroku";

    // CIEMNE NYLIUM

    private static final Identifier CIEMNE_NYLIUM_ID =
            Identifier.of(MOD_ID, "ciemne_nylium");

    public static final RegistryKey<Block> CIEMNE_NYLIUM_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, CIEMNE_NYLIUM_ID);

    public static final RegistryKey<Item> CIEMNE_NYLIUM_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, CIEMNE_NYLIUM_ID);

    public static final Block CIEMNE_NYLIUM = Registry.register(
            Registries.BLOCK,
            CIEMNE_NYLIUM_ID,
            new CiemneNyliumBlock(AbstractBlock.Settings.copy(Blocks.END_STONE)
                    .ticksRandomly()
                    .strength(3.0F, 9.0F)
                    .requiresTool()
                    .registryKey(CIEMNE_NYLIUM_KEY)
                    .mapColor(MapColor.PURPLE))
    );

    public static final Item CIEMNE_NYLIUM_ITEM = Registry.register(
            Registries.ITEM,
            CIEMNE_NYLIUM_ID,
            new BlockItem(CIEMNE_NYLIUM, new Item.Settings()
                    .registryKey(CIEMNE_NYLIUM_ITEM_KEY))
    );

    // MROCZNE KORZENIE

    private static final Identifier MROCZNE_KORZENIE_ID =
            Identifier.of(MOD_ID, "mroczne_korzenie");

    public static final RegistryKey<Block> MROCZNE_KORZENIE_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, MROCZNE_KORZENIE_ID);

    public static final RegistryKey<Item> MROCZNE_KORZENIE_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, MROCZNE_KORZENIE_ID);

    public static final Block MROCZNE_KORZENIE = Registry.register(
            Registries.BLOCK,
            MROCZNE_KORZENIE_ID,
            new MroczneKorzenieBlock(
                    AbstractBlock.Settings.copy(Blocks.WARPED_ROOTS)
                            .nonOpaque()
                            .registryKey(MROCZNE_KORZENIE_KEY)
                            .mapColor(MapColor.PURPLE)
            )
    );

    public static final Item MROCZNE_KORZENIE_ITEM = Registry.register(
            Registries.ITEM,
            MROCZNE_KORZENIE_ID,
            new BlockItem(MROCZNE_KORZENIE, new Item.Settings()
                    .registryKey(MROCZNE_KORZENIE_ITEM_KEY))
    );

    // DARK SPROUTS

    private static final Identifier DARK_SPROUTS_ID =
            Identifier.of(MOD_ID, "dark_sprouts");

    public static final RegistryKey<Block> DARK_SPROUTS_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, DARK_SPROUTS_ID);

    public static final RegistryKey<Item> DARK_SPROUTS_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, DARK_SPROUTS_ID);

    public static final Block DARK_SPROUTS = Registry.register(
            Registries.BLOCK,
            DARK_SPROUTS_ID,
            new DarkSproutsBlock(
                    AbstractBlock.Settings.copy(Blocks.NETHER_SPROUTS)
                            .nonOpaque()
                            .registryKey(DARK_SPROUTS_KEY)
            )
    );

    public static final Item DARK_SPROUTS_ITEM = Registry.register(
            Registries.ITEM,
            DARK_SPROUTS_ID,
            new BlockItem(DARK_SPROUTS, new Item.Settings()
                    .registryKey(DARK_SPROUTS_ITEM_KEY))
    );

    // DARK FUNGUS

    private static final Identifier DARK_FUNGUS_ID =
            Identifier.of(MOD_ID, "dark_fungus");

    public static final RegistryKey<Block> DARK_FUNGUS_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, DARK_FUNGUS_ID);

    public static final RegistryKey<Item> DARK_FUNGUS_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, DARK_FUNGUS_ID);

    public static final Block DARK_FUNGUS = Registry.register(
            Registries.BLOCK,
            DARK_FUNGUS_ID,
            new DarkFungusBlock(
                    AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM)
                            .nonOpaque()
                            .registryKey(DARK_FUNGUS_KEY)
            )
    );

    public static final Item DARK_FUNGUS_ITEM = Registry.register(
            Registries.ITEM,
            DARK_FUNGUS_ID,
            new BlockItem(DARK_FUNGUS, new Item.Settings()
                    .registryKey(DARK_FUNGUS_ITEM_KEY))
    );

    // DARK STEM

    private static final Identifier DARK_STEM_ID =
            Identifier.of(MOD_ID, "dark_stem");

    public static final RegistryKey<Block> DARK_STEM_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, DARK_STEM_ID);

    public static final RegistryKey<Item> DARK_STEM_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, DARK_STEM_ID);

    public static final Block DARK_STEM = Registry.register(
            Registries.BLOCK,
            DARK_STEM_ID,
            new DarkStemBlock(
                    AbstractBlock.Settings.copy(Blocks.CRIMSON_STEM)
                            .registryKey(DARK_STEM_KEY)
            )
    );

    public static final Item DARK_STEM_ITEM = Registry.register(
            Registries.ITEM,
            DARK_STEM_ID,
            new BlockItem(DARK_STEM, new Item.Settings()
                    .registryKey(DARK_STEM_ITEM_KEY))
    );


    // DARK WART BLOCK

    private static final Identifier DARK_WART_BLOCK_ID =
            Identifier.of(MOD_ID, "dark_wart_block");

    public static final RegistryKey<Block> DARK_WART_BLOCK_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, DARK_WART_BLOCK_ID);

    public static final RegistryKey<Item> DARK_WART_BLOCK_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, DARK_WART_BLOCK_ID);

    public static final Block DARK_WART_BLOCK = Registry.register(
            Registries.BLOCK,
            DARK_WART_BLOCK_ID,
            new Block(
                    AbstractBlock.Settings.copy(Blocks.WARPED_WART_BLOCK)
                            .registryKey(DARK_WART_BLOCK_KEY)
            )
    );

    public static final Item DARK_WART_BLOCK_ITEM = Registry.register(
            Registries.ITEM,
            DARK_WART_BLOCK_ID,
            new BlockItem(DARK_WART_BLOCK, new Item.Settings()
                    .registryKey(DARK_WART_BLOCK_ITEM_KEY))
    );

    // STRIPPED DARK STEM

private static final Identifier STRIPPED_DARK_STEM_ID =
        Identifier.of(MOD_ID, "stripped_dark_stem");

public static final RegistryKey<Block> STRIPPED_DARK_STEM_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, STRIPPED_DARK_STEM_ID);

public static final RegistryKey<Item> STRIPPED_DARK_STEM_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, STRIPPED_DARK_STEM_ID);

public static final Block STRIPPED_DARK_STEM = Registry.register(
        Registries.BLOCK,
        STRIPPED_DARK_STEM_ID,
        new DarkStemBlock(
                AbstractBlock.Settings.copy(Blocks.STRIPPED_CRIMSON_STEM)
                        .registryKey(STRIPPED_DARK_STEM_KEY)
        )
);

public static final Item STRIPPED_DARK_STEM_ITEM = Registry.register(
        Registries.ITEM,
        STRIPPED_DARK_STEM_ID,
        new BlockItem(STRIPPED_DARK_STEM, new Item.Settings()
                .registryKey(STRIPPED_DARK_STEM_ITEM_KEY))
);


// DARK PLANKS

private static final Identifier DARK_PLANKS_ID =
        Identifier.of(MOD_ID, "dark_planks");

public static final RegistryKey<Block> DARK_PLANKS_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_PLANKS_ID);

public static final RegistryKey<Item> DARK_PLANKS_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_PLANKS_ID);

public static final Block DARK_PLANKS = Registry.register(
        Registries.BLOCK,
        DARK_PLANKS_ID,
        new Block(
                AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
                        .registryKey(DARK_PLANKS_KEY)
        )
);

public static final Item DARK_PLANKS_ITEM = Registry.register(
        Registries.ITEM,
        DARK_PLANKS_ID,
        new BlockItem(DARK_PLANKS, new Item.Settings()
                .registryKey(DARK_PLANKS_ITEM_KEY))
);


// DARK HYPHAE

private static final Identifier DARK_HYPHAE_ID =
        Identifier.of(MOD_ID, "dark_hyphae");

public static final RegistryKey<Block> DARK_HYPHAE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_HYPHAE_ID);

public static final RegistryKey<Item> DARK_HYPHAE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_HYPHAE_ID);

public static final Block DARK_HYPHAE = Registry.register(
        Registries.BLOCK,
        DARK_HYPHAE_ID,
        new PillarBlock(
                AbstractBlock.Settings.copy(Blocks.CRIMSON_HYPHAE)
                        .registryKey(DARK_HYPHAE_KEY)
        )
);

public static final Item DARK_HYPHAE_ITEM = Registry.register(
        Registries.ITEM,
        DARK_HYPHAE_ID,
        new BlockItem(DARK_HYPHAE, new Item.Settings()
                .registryKey(DARK_HYPHAE_ITEM_KEY))
);

// STRIPPED DARK HYPHAE

private static final Identifier STRIPPED_DARK_HYPHAE_ID =
        Identifier.of(MOD_ID, "stripped_dark_hyphae");

public static final RegistryKey<Block> STRIPPED_DARK_HYPHAE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, STRIPPED_DARK_HYPHAE_ID);

public static final RegistryKey<Item> STRIPPED_DARK_HYPHAE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, STRIPPED_DARK_HYPHAE_ID);

public static final Block STRIPPED_DARK_HYPHAE = Registry.register(
        Registries.BLOCK,
        STRIPPED_DARK_HYPHAE_ID,
        new PillarBlock(
                AbstractBlock.Settings.copy(Blocks.STRIPPED_CRIMSON_HYPHAE)
                        .registryKey(STRIPPED_DARK_HYPHAE_KEY)
        )
);

public static final Item STRIPPED_DARK_HYPHAE_ITEM = Registry.register(
        Registries.ITEM,
        STRIPPED_DARK_HYPHAE_ID,
        new BlockItem(STRIPPED_DARK_HYPHAE, new Item.Settings()
                .registryKey(STRIPPED_DARK_HYPHAE_ITEM_KEY))
);

// DARK SLAB

private static final Identifier DARK_SLAB_ID =
        Identifier.of(MOD_ID, "dark_slab");

public static final RegistryKey<Block> DARK_SLAB_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_SLAB_ID);

public static final RegistryKey<Item> DARK_SLAB_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_SLAB_ID);

public static final Block DARK_SLAB = Registry.register(
        Registries.BLOCK,
        DARK_SLAB_ID,
        new SlabBlock(
                AbstractBlock.Settings.copy(Blocks.OAK_SLAB)
                        .registryKey(DARK_SLAB_KEY)
        )
);

public static final Item DARK_SLAB_ITEM = Registry.register(
        Registries.ITEM,
        DARK_SLAB_ID,
        new BlockItem(
                DARK_SLAB,
                new Item.Settings().registryKey(DARK_SLAB_ITEM_KEY)
        )
);

// DARK FENCE

private static final Identifier DARK_FENCE_ID =
        Identifier.of(MOD_ID, "dark_fence");

public static final RegistryKey<Block> DARK_FENCE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_FENCE_ID);

public static final RegistryKey<Item> DARK_FENCE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_FENCE_ID);

public static final Block DARK_FENCE = Registry.register(
        Registries.BLOCK,
        DARK_FENCE_ID,
        new FenceBlock(
                AbstractBlock.Settings.copy(Blocks.OAK_FENCE)
                        .registryKey(DARK_FENCE_KEY)
        )
);

public static final Item DARK_FENCE_ITEM = Registry.register(
        Registries.ITEM,
        DARK_FENCE_ID,
        new BlockItem(
                DARK_FENCE,
                new Item.Settings().registryKey(DARK_FENCE_ITEM_KEY)
        )
);

// DARK FENCE GATE

private static final Identifier DARK_FENCE_GATE_ID =
        Identifier.of(MOD_ID, "dark_fence_gate");

public static final RegistryKey<Block> DARK_FENCE_GATE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_FENCE_GATE_ID);

public static final RegistryKey<Item> DARK_FENCE_GATE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_FENCE_GATE_ID);

public static final Block DARK_FENCE_GATE = Registry.register(
        Registries.BLOCK,
        DARK_FENCE_GATE_ID,
        new FenceGateBlock(
                WoodType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)
                        .registryKey(DARK_FENCE_GATE_KEY)
        )
);

public static final Item DARK_FENCE_GATE_ITEM = Registry.register(
        Registries.ITEM,
        DARK_FENCE_GATE_ID,
        new BlockItem(
                DARK_FENCE_GATE,
                new Item.Settings().registryKey(DARK_FENCE_GATE_ITEM_KEY)
        )
);

// DARK BUTTON
private static final Identifier DARK_BUTTON_ID =
        Identifier.of(MOD_ID, "dark_button");

public static final RegistryKey<Block> DARK_BUTTON_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_BUTTON_ID);

public static final RegistryKey<Item> DARK_BUTTON_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_BUTTON_ID);

public static final Block DARK_BUTTON = Registry.register(
        Registries.BLOCK,
        DARK_BUTTON_ID,
        new ButtonBlock(
                BlockSetType.OAK,
                30,
                AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)
                        .registryKey(DARK_BUTTON_KEY)
        )
);

public static final Item DARK_BUTTON_ITEM = Registry.register(
        Registries.ITEM,
        DARK_BUTTON_ID,
        new BlockItem(
                DARK_BUTTON,
                new Item.Settings().registryKey(DARK_BUTTON_ITEM_KEY)
        )
);

// DARK PRESSURE PLATE
private static final Identifier DARK_PRESSURE_PLATE_ID =
        Identifier.of(MOD_ID, "dark_pressure_plate");

public static final RegistryKey<Block> DARK_PRESSURE_PLATE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_PRESSURE_PLATE_ID);

public static final RegistryKey<Item> DARK_PRESSURE_PLATE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_PRESSURE_PLATE_ID);

public static final Block DARK_PRESSURE_PLATE = Registry.register(
        Registries.BLOCK,
        DARK_PRESSURE_PLATE_ID,
        new PressurePlateBlock(
                BlockSetType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)
                        .registryKey(DARK_PRESSURE_PLATE_KEY)
        )
);

public static final Item DARK_PRESSURE_PLATE_ITEM = Registry.register(
        Registries.ITEM,
        DARK_PRESSURE_PLATE_ID,
        new BlockItem(
                DARK_PRESSURE_PLATE,
                new Item.Settings().registryKey(DARK_PRESSURE_PLATE_ITEM_KEY)
        )
);

// DARK STAIRS
private static final Identifier DARK_STAIRS_ID =
        Identifier.of(MOD_ID, "dark_stairs");

public static final RegistryKey<Block> DARK_STAIRS_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_STAIRS_ID);

public static final RegistryKey<Item> DARK_STAIRS_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_STAIRS_ID);

public static final Block DARK_STAIRS = Registry.register(
        Registries.BLOCK,
        DARK_STAIRS_ID,
        new StairsBlock(
                DARK_PLANKS.getDefaultState(),
                AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)
                        .registryKey(DARK_STAIRS_KEY)
        )
);

public static final Item DARK_STAIRS_ITEM = Registry.register(
        Registries.ITEM,
        DARK_STAIRS_ID,
        new BlockItem(
                DARK_STAIRS,
                new Item.Settings().registryKey(DARK_STAIRS_ITEM_KEY)
        )
);

// DARK WOOD TYPE
public static final WoodType DARK_WOOD_TYPE =
        new WoodTypeBuilder().register(
                Identifier.of(MOD_ID, "dark"),
                BlockSetType.OAK
        );

 // DARK SIGN
private static final Identifier DARK_SIGN_ID =
        Identifier.of(MOD_ID, "dark_sign");

public static final RegistryKey<Block> DARK_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_SIGN_ID);

public static final Block DARK_SIGN = Registry.register(
        Registries.BLOCK,
        DARK_SIGN_ID,
        new SignBlock(
                DARK_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_SIGN)
                        .registryKey(DARK_SIGN_KEY)
        )
);

// DARK WALL SIGN
private static final Identifier DARK_WALL_SIGN_ID =
        Identifier.of(MOD_ID, "dark_wall_sign");

public static final RegistryKey<Block> DARK_WALL_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_WALL_SIGN_ID);

public static final Block DARK_WALL_SIGN = Registry.register(
        Registries.BLOCK,
        DARK_WALL_SIGN_ID,
        new WallSignBlock(
                DARK_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)
                        .registryKey(DARK_WALL_SIGN_KEY)
        )
);

// DARK SIGN ITEM
private static final Identifier DARK_SIGN_ITEM_ID =
        Identifier.of(MOD_ID, "dark_sign");

public static final RegistryKey<Item> DARK_SIGN_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_SIGN_ITEM_ID);

public static final Item DARK_SIGN_ITEM = Registry.register(
        Registries.ITEM,
        DARK_SIGN_ITEM_ID,
        new SignItem(
                DARK_SIGN,
                DARK_WALL_SIGN,
                new Item.Settings().registryKey(DARK_SIGN_ITEM_KEY)
        )
);


// DARK TRAPDOOR
private static final Identifier DARK_TRAPDOOR_ID =
        Identifier.of(MOD_ID, "dark_trapdoor");

public static final RegistryKey<Block> DARK_TRAPDOOR_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_TRAPDOOR_ID);

public static final RegistryKey<Item> DARK_TRAPDOOR_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_TRAPDOOR_ID);

public static final Block DARK_TRAPDOOR = Registry.register(
        Registries.BLOCK,
        DARK_TRAPDOOR_ID,
        new TrapdoorBlock(
                BlockSetType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)
                        .registryKey(DARK_TRAPDOOR_KEY)
        )
);

public static final Item DARK_TRAPDOOR_ITEM = Registry.register(
        Registries.ITEM,
        DARK_TRAPDOOR_ID,
        new BlockItem(
                DARK_TRAPDOOR,
                new Item.Settings().registryKey(DARK_TRAPDOOR_ITEM_KEY)
        )
);

// DARK HANGING SIGN
private static final Identifier DARK_HANGING_SIGN_ID =
        Identifier.of(MOD_ID, "dark_hanging_sign");

public static final RegistryKey<Block> DARK_HANGING_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_HANGING_SIGN_ID);

public static final RegistryKey<Item> DARK_HANGING_SIGN_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_HANGING_SIGN_ID);

public static final Block DARK_HANGING_SIGN = Registry.register(
        Registries.BLOCK,
        DARK_HANGING_SIGN_ID,
        new HangingSignBlock(
                DARK_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)
                        .registryKey(DARK_HANGING_SIGN_KEY)
        )
);

// DARK WALL HANGING SIGN
private static final Identifier DARK_WALL_HANGING_SIGN_ID =
        Identifier.of(MOD_ID, "dark_wall_hanging_sign");

public static final RegistryKey<Block> DARK_WALL_HANGING_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_WALL_HANGING_SIGN_ID);

public static final Block DARK_WALL_HANGING_SIGN = Registry.register(
        Registries.BLOCK,
        DARK_WALL_HANGING_SIGN_ID,
        new WallHangingSignBlock(
                DARK_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN)
                        .registryKey(DARK_WALL_HANGING_SIGN_KEY)
        )
);

// DARK HANGING SIGN ITEM
public static final Item DARK_HANGING_SIGN_ITEM = Registry.register(
        Registries.ITEM,
        DARK_HANGING_SIGN_ID,
        new HangingSignItem(
                DARK_HANGING_SIGN,
                DARK_WALL_HANGING_SIGN,
                new Item.Settings().registryKey(DARK_HANGING_SIGN_ITEM_KEY)
        )
);

// DARK DOOR
private static final Identifier DARK_DOOR_ID =
        Identifier.of(MOD_ID, "dark_door");

public static final RegistryKey<Block> DARK_DOOR_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_DOOR_ID);

public static final RegistryKey<Item> DARK_DOOR_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_DOOR_ID);

public static final Block DARK_DOOR = Registry.register(
        Registries.BLOCK,
        DARK_DOOR_ID,
        new DoorBlock(
                BlockSetType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_DOOR)
                        .registryKey(DARK_DOOR_KEY)
        )
);

public static final Item DARK_DOOR_ITEM = Registry.register(
        Registries.ITEM,
        DARK_DOOR_ID,
        new BlockItem(
                DARK_DOOR,
                new Item.Settings().registryKey(DARK_DOOR_ITEM_KEY)
        )
);

// DARK BOAT

private static final Identifier DARK_BOAT_ID =
        Identifier.of(MOD_ID, "dark_boat");

public static final RegistryKey<EntityType<?>> DARK_BOAT_ENTITY_KEY =
        RegistryKey.of(RegistryKeys.ENTITY_TYPE, DARK_BOAT_ID);

public static final EntityType<BoatEntity> DARK_BOAT_ENTITY = Registry.register(
        Registries.ENTITY_TYPE,
        DARK_BOAT_ID,
        EntityType.Builder.<BoatEntity>create(
                (type, world) -> new BoatEntity(
                        type,
                        world,
                        EndusMroku::getDarkBoatItem
                ),
                SpawnGroup.MISC
        )
                .dimensions(1.375F, 0.5625F)
                .maxTrackingRange(10)
                .trackingTickInterval(3)
                .build(DARK_BOAT_ENTITY_KEY)
);

public static final RegistryKey<Item> DARK_BOAT_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_BOAT_ID);

public static final Item DARK_BOAT = Registry.register(
        Registries.ITEM,
        DARK_BOAT_ID,
        new BoatItem(
                DARK_BOAT_ENTITY,
                new Item.Settings().registryKey(DARK_BOAT_ITEM_KEY)
        )
);

private static Item getDarkBoatItem() {
    return DARK_BOAT;
}

// DARK CHEST BOAT

private static final Identifier DARK_CHEST_BOAT_ID =
        Identifier.of(MOD_ID, "dark_chest_boat");

public static final RegistryKey<EntityType<?>> DARK_CHEST_BOAT_ENTITY_KEY =
        RegistryKey.of(RegistryKeys.ENTITY_TYPE, DARK_CHEST_BOAT_ID);

public static final EntityType<ChestBoatEntity> DARK_CHEST_BOAT_ENTITY = Registry.register(
        Registries.ENTITY_TYPE,
        DARK_CHEST_BOAT_ID,
        EntityType.Builder.<ChestBoatEntity>create(
                (type, world) -> new ChestBoatEntity(
                        type,
                        world,
                        EndusMroku::getDarkChestBoatItem
                ),
                SpawnGroup.MISC
        )
                .dimensions(1.375F, 0.5625F)
                .maxTrackingRange(10)
                .trackingTickInterval(3)
                .build(DARK_CHEST_BOAT_ENTITY_KEY)
);

public static final RegistryKey<Item> DARK_CHEST_BOAT_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_CHEST_BOAT_ID);

public static final Item DARK_CHEST_BOAT = Registry.register(
        Registries.ITEM,
        DARK_CHEST_BOAT_ID,
        new BoatItem(
                DARK_CHEST_BOAT_ENTITY,
                new Item.Settings().registryKey(DARK_CHEST_BOAT_ITEM_KEY)
        )
);

private static Item getDarkChestBoatItem() {
    return DARK_CHEST_BOAT;
}

// DARK TWISTING VINES

public static final DarkTwistingVinesPlantBlock DARK_TWISTING_VINES_PLANT =
        Registry.register(
                Registries.BLOCK,
                Identifier.of("endusmroku", "dark_twisting_vines_plant"),
                new DarkTwistingVinesPlantBlock(
                        Block.Settings.copy(Blocks.TWISTING_VINES_PLANT)
                                .registryKey(RegistryKey.of(
                                        RegistryKeys.BLOCK,
                                        Identifier.of("endusmroku", "dark_twisting_vines_plant")
                                ))
                )
        );

public static final DarkTwistingVinesBlock DARK_TWISTING_VINES =
        Registry.register(
                Registries.BLOCK,
                Identifier.of("endusmroku", "dark_twisting_vines"),
                new DarkTwistingVinesBlock(
                        Block.Settings.copy(Blocks.TWISTING_VINES)
                                .registryKey(RegistryKey.of(
                                        RegistryKeys.BLOCK,
                                        Identifier.of("endusmroku", "dark_twisting_vines")
                                ))
                )
        );

public static final BlockItem DARK_TWISTING_VINES_ITEM =
        Registry.register(
                Registries.ITEM,
                Identifier.of("endusmroku", "dark_twisting_vines"),
                new BlockItem(
                        DARK_TWISTING_VINES,
                        new Item.Settings().registryKey(RegistryKey.of(
                                RegistryKeys.ITEM,
                                Identifier.of("endusmroku", "dark_twisting_vines")
                        ))
                )
        );

// DARK WEEPING VINES

private static final Identifier DARK_WEEPING_VINES_ID =
        Identifier.of(MOD_ID, "dark_weeping_vines");

private static final Identifier DARK_WEEPING_VINES_PLANT_ID =
        Identifier.of(MOD_ID, "dark_weeping_vines_plant");

private static final RegistryKey<Block> DARK_WEEPING_VINES_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_WEEPING_VINES_ID);

private static final RegistryKey<Block> DARK_WEEPING_VINES_PLANT_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, DARK_WEEPING_VINES_PLANT_ID);

private static final RegistryKey<Item> DARK_WEEPING_VINES_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, DARK_WEEPING_VINES_ID);

public static final DarkWeepingVinesPlantBlock DARK_WEEPING_VINES_PLANT =
        Registry.register(
                Registries.BLOCK,
                DARK_WEEPING_VINES_PLANT_ID,
                new DarkWeepingVinesPlantBlock(
                        AbstractBlock.Settings.copy(Blocks.WEEPING_VINES_PLANT)
                                .registryKey(DARK_WEEPING_VINES_PLANT_KEY)
                )
        );

public static final DarkWeepingVinesBlock DARK_WEEPING_VINES =
        Registry.register(
                Registries.BLOCK,
                DARK_WEEPING_VINES_ID,
                new DarkWeepingVinesBlock(
                        AbstractBlock.Settings.copy(Blocks.WEEPING_VINES)
                                .registryKey(DARK_WEEPING_VINES_KEY)
                )
        );

public static final Item DARK_WEEPING_VINES_ITEM =
        Registry.register(
                Registries.ITEM,
                DARK_WEEPING_VINES_ID,
                new BlockItem(
                        DARK_WEEPING_VINES,
                        new Item.Settings()
                                .registryKey(DARK_WEEPING_VINES_ITEM_KEY)
                )
        );


    @Override
public void onInitialize() {

    // Okorowywanie Mrocznego Trzonu
    StrippableBlockRegistry.register(
            DARK_STEM,
            STRIPPED_DARK_STEM
    );

    // Okorowywanie Mrocznych Strzępków
    StrippableBlockRegistry.register(
            DARK_HYPHAE,
            STRIPPED_DARK_HYPHAE
    );

    BlockEntityType.SIGN.addSupportedBlock(DARK_SIGN);
    BlockEntityType.SIGN.addSupportedBlock(DARK_WALL_SIGN);

    BlockEntityType.HANGING_SIGN.addSupportedBlock(DARK_HANGING_SIGN);
    BlockEntityType.HANGING_SIGN.addSupportedBlock(DARK_WALL_HANGING_SIGN); 

}
}