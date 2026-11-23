
package pl.enderwilds;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
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
import pl.enderwilds.block.EnderTwistingVinesBlock;
import pl.enderwilds.block.EnderTwistingVinesPlantBlock;
import pl.enderwilds.block.EnderWeepingVinesBlock;
import pl.enderwilds.block.EnderWeepingVinesPlantBlock;
import pl.enderwilds.block.AshenTwistingVinesBlock;
import pl.enderwilds.block.AshenTwistingVinesPlantBlock;
import pl.enderwilds.block.AshenWeepingVinesBlock;
import pl.enderwilds.block.AshenWeepingVinesPlantBlock;

public class Enderwilds implements ModInitializer {
    public static final String MOD_ID = "enderwilds";

    // CIEMNE NYLIUM

    private static final Identifier ENDER_NYLIUM_ID =
            Identifier.of(MOD_ID, "ender_nylium");

    public static final RegistryKey<Block> ENDER_NYLIUM_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ENDER_NYLIUM_ID);

    public static final RegistryKey<Item> ENDER_NYLIUM_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ENDER_NYLIUM_ID);

    public static final Block ENDER_NYLIUM = Registry.register(
            Registries.BLOCK,
            ENDER_NYLIUM_ID,
            new EnderNyliumBlock(AbstractBlock.Settings.copy(Blocks.END_STONE)
                    .ticksRandomly()
                    .strength(1.5F, 6.0F)
                    .requiresTool()
                    .registryKey(ENDER_NYLIUM_KEY)
                    .mapColor(MapColor.PURPLE))
    );

    public static final Item ENDER_NYLIUM_ITEM = Registry.register(
            Registries.ITEM,
            ENDER_NYLIUM_ID,
            new BlockItem(ENDER_NYLIUM, new Item.Settings()
                    .registryKey(ENDER_NYLIUM_ITEM_KEY))
    );

    // MROCZNE KORZENIE

    private static final Identifier ENDER_ROOTS_ID =
            Identifier.of(MOD_ID, "ender_roots");

    public static final RegistryKey<Block> ENDER_ROOTS_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ENDER_ROOTS_ID);

    public static final RegistryKey<Item> ENDER_ROOTS_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ENDER_ROOTS_ID);

    public static final Block ENDER_ROOTS = Registry.register(
            Registries.BLOCK,
            ENDER_ROOTS_ID,
            new EnderRootsBlock(
                    AbstractBlock.Settings.copy(Blocks.WARPED_ROOTS)
                            .nonOpaque()
                            .registryKey(ENDER_ROOTS_KEY)
                            .mapColor(MapColor.PURPLE)
            )
    );

    public static final Item ENDER_ROOTS_ITEM = Registry.register(
            Registries.ITEM,
            ENDER_ROOTS_ID,
            new BlockItem(ENDER_ROOTS, new Item.Settings()
                    .registryKey(ENDER_ROOTS_ITEM_KEY))
    );

    // DARK SPROUTS

    private static final Identifier ENDER_SPROUTS_ID =
            Identifier.of(MOD_ID, "ender_sprouts");

    public static final RegistryKey<Block> ENDER_SPROUTS_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ENDER_SPROUTS_ID);

    public static final RegistryKey<Item> ENDER_SPROUTS_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ENDER_SPROUTS_ID);

    public static final Block ENDER_SPROUTS = Registry.register(
            Registries.BLOCK,
            ENDER_SPROUTS_ID,
            new EnderSproutsBlock(
                    AbstractBlock.Settings.copy(Blocks.NETHER_SPROUTS)
                            .nonOpaque()
                            .registryKey(ENDER_SPROUTS_KEY)
            )
    );

    public static final Item ENDER_SPROUTS_ITEM = Registry.register(
            Registries.ITEM,
            ENDER_SPROUTS_ID,
            new BlockItem(ENDER_SPROUTS, new Item.Settings()
                    .registryKey(ENDER_SPROUTS_ITEM_KEY))
    );

    // DARK FUNGUS

    private static final Identifier ENDER_FUNGUS_ID =
            Identifier.of(MOD_ID, "ender_fungus");

    public static final RegistryKey<Block> ENDER_FUNGUS_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ENDER_FUNGUS_ID);

    public static final RegistryKey<Item> ENDER_FUNGUS_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ENDER_FUNGUS_ID);

    public static final Block ENDER_FUNGUS = Registry.register(
            Registries.BLOCK,
            ENDER_FUNGUS_ID,
            new EnderFungusBlock(
                    AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM)
                            .nonOpaque()
                            .registryKey(ENDER_FUNGUS_KEY)
            )
    );

    public static final Item ENDER_FUNGUS_ITEM = Registry.register(
            Registries.ITEM,
            ENDER_FUNGUS_ID,
            new BlockItem(ENDER_FUNGUS, new Item.Settings()
                    .registryKey(ENDER_FUNGUS_ITEM_KEY))
    );

    // DARK STEM

    private static final Identifier ENDER_STEM_ID =
            Identifier.of(MOD_ID, "ender_stem");

    public static final RegistryKey<Block> ENDER_STEM_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ENDER_STEM_ID);

    public static final RegistryKey<Item> ENDER_STEM_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ENDER_STEM_ID);

    public static final Block ENDER_STEM = Registry.register(
            Registries.BLOCK,
            ENDER_STEM_ID,
            new EnderStemBlock(
                    AbstractBlock.Settings.copy(Blocks.CRIMSON_STEM)
                            .registryKey(ENDER_STEM_KEY)
            )
    );

    public static final Item ENDER_STEM_ITEM = Registry.register(
            Registries.ITEM,
            ENDER_STEM_ID,
            new BlockItem(ENDER_STEM, new Item.Settings()
                    .registryKey(ENDER_STEM_ITEM_KEY))
    );


    // DARK WART BLOCK

    private static final Identifier ENDER_WART_BLOCK_ID =
            Identifier.of(MOD_ID, "ender_wart_block");

    public static final RegistryKey<Block> ENDER_WART_BLOCK_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ENDER_WART_BLOCK_ID);

    public static final RegistryKey<Item> ENDER_WART_BLOCK_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ENDER_WART_BLOCK_ID);

    public static final Block ENDER_WART_BLOCK = Registry.register(
            Registries.BLOCK,
            ENDER_WART_BLOCK_ID,
            new Block(
                    AbstractBlock.Settings.copy(Blocks.WARPED_WART_BLOCK)
                            .registryKey(ENDER_WART_BLOCK_KEY)
            )
    );

    public static final Item ENDER_WART_BLOCK_ITEM = Registry.register(
            Registries.ITEM,
            ENDER_WART_BLOCK_ID,
            new BlockItem(ENDER_WART_BLOCK, new Item.Settings()
                    .registryKey(ENDER_WART_BLOCK_ITEM_KEY))
    );

    // STRIPPED DARK STEM

private static final Identifier STRIPPED_ENDER_STEM_ID =
        Identifier.of(MOD_ID, "stripped_ender_stem");

public static final RegistryKey<Block> STRIPPED_ENDER_STEM_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, STRIPPED_ENDER_STEM_ID);

public static final RegistryKey<Item> STRIPPED_ENDER_STEM_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, STRIPPED_ENDER_STEM_ID);

public static final Block STRIPPED_ENDER_STEM = Registry.register(
        Registries.BLOCK,
        STRIPPED_ENDER_STEM_ID,
        new EnderStemBlock(
                AbstractBlock.Settings.copy(Blocks.STRIPPED_CRIMSON_STEM)
                        .registryKey(STRIPPED_ENDER_STEM_KEY)
        )
);

public static final Item STRIPPED_ENDER_STEM_ITEM = Registry.register(
        Registries.ITEM,
        STRIPPED_ENDER_STEM_ID,
        new BlockItem(STRIPPED_ENDER_STEM, new Item.Settings()
                .registryKey(STRIPPED_ENDER_STEM_ITEM_KEY))
);


// DARK PLANKS

private static final Identifier ENDER_PLANKS_ID =
        Identifier.of(MOD_ID, "ender_planks");

public static final RegistryKey<Block> ENDER_PLANKS_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_PLANKS_ID);

public static final RegistryKey<Item> ENDER_PLANKS_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_PLANKS_ID);

public static final Block ENDER_PLANKS = Registry.register(
        Registries.BLOCK,
        ENDER_PLANKS_ID,
        new Block(
                AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
                        .registryKey(ENDER_PLANKS_KEY)
        )
);

public static final Item ENDER_PLANKS_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_PLANKS_ID,
        new BlockItem(ENDER_PLANKS, new Item.Settings()
                .registryKey(ENDER_PLANKS_ITEM_KEY))
);


// DARK HYPHAE

private static final Identifier ENDER_HYPHAE_ID =
        Identifier.of(MOD_ID, "ender_hyphae");

public static final RegistryKey<Block> ENDER_HYPHAE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_HYPHAE_ID);

public static final RegistryKey<Item> ENDER_HYPHAE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_HYPHAE_ID);

public static final Block ENDER_HYPHAE = Registry.register(
        Registries.BLOCK,
        ENDER_HYPHAE_ID,
        new PillarBlock(
                AbstractBlock.Settings.copy(Blocks.CRIMSON_HYPHAE)
                        .registryKey(ENDER_HYPHAE_KEY)
        )
);

public static final Item ENDER_HYPHAE_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_HYPHAE_ID,
        new BlockItem(ENDER_HYPHAE, new Item.Settings()
                .registryKey(ENDER_HYPHAE_ITEM_KEY))
);

// STRIPPED DARK HYPHAE

private static final Identifier STRIPPED_ENDER_HYPHAE_ID =
        Identifier.of(MOD_ID, "stripped_ender_hyphae");

public static final RegistryKey<Block> STRIPPED_ENDER_HYPHAE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, STRIPPED_ENDER_HYPHAE_ID);

public static final RegistryKey<Item> STRIPPED_ENDER_HYPHAE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, STRIPPED_ENDER_HYPHAE_ID);

public static final Block STRIPPED_ENDER_HYPHAE = Registry.register(
        Registries.BLOCK,
        STRIPPED_ENDER_HYPHAE_ID,
        new PillarBlock(
                AbstractBlock.Settings.copy(Blocks.STRIPPED_CRIMSON_HYPHAE)
                        .registryKey(STRIPPED_ENDER_HYPHAE_KEY)
        )
);

public static final Item STRIPPED_ENDER_HYPHAE_ITEM = Registry.register(
        Registries.ITEM,
        STRIPPED_ENDER_HYPHAE_ID,
        new BlockItem(STRIPPED_ENDER_HYPHAE, new Item.Settings()
                .registryKey(STRIPPED_ENDER_HYPHAE_ITEM_KEY))
);

// DARK SLAB

private static final Identifier ENDER_SLAB_ID =
        Identifier.of(MOD_ID, "ender_slab");

public static final RegistryKey<Block> ENDER_SLAB_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_SLAB_ID);

public static final RegistryKey<Item> ENDER_SLAB_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_SLAB_ID);

public static final Block ENDER_SLAB = Registry.register(
        Registries.BLOCK,
        ENDER_SLAB_ID,
        new SlabBlock(
                AbstractBlock.Settings.copy(Blocks.OAK_SLAB)
                        .registryKey(ENDER_SLAB_KEY)
        )
);

public static final Item ENDER_SLAB_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_SLAB_ID,
        new BlockItem(
                ENDER_SLAB,
                new Item.Settings().registryKey(ENDER_SLAB_ITEM_KEY)
        )
);

// DARK FENCE

private static final Identifier ENDER_FENCE_ID =
        Identifier.of(MOD_ID, "ender_fence");

public static final RegistryKey<Block> ENDER_FENCE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_FENCE_ID);

public static final RegistryKey<Item> ENDER_FENCE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_FENCE_ID);

public static final Block ENDER_FENCE = Registry.register(
        Registries.BLOCK,
        ENDER_FENCE_ID,
        new FenceBlock(
                AbstractBlock.Settings.copy(Blocks.OAK_FENCE)
                        .registryKey(ENDER_FENCE_KEY)
        )
);

public static final Item ENDER_FENCE_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_FENCE_ID,
        new BlockItem(
                ENDER_FENCE,
                new Item.Settings().registryKey(ENDER_FENCE_ITEM_KEY)
        )
);

// DARK FENCE GATE

private static final Identifier ENDER_FENCE_GATE_ID =
        Identifier.of(MOD_ID, "ender_fence_gate");

public static final RegistryKey<Block> ENDER_FENCE_GATE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_FENCE_GATE_ID);

public static final RegistryKey<Item> ENDER_FENCE_GATE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_FENCE_GATE_ID);

public static final Block ENDER_FENCE_GATE = Registry.register(
        Registries.BLOCK,
        ENDER_FENCE_GATE_ID,
        new FenceGateBlock(
                WoodType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)
                        .registryKey(ENDER_FENCE_GATE_KEY)
        )
);

public static final Item ENDER_FENCE_GATE_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_FENCE_GATE_ID,
        new BlockItem(
                ENDER_FENCE_GATE,
                new Item.Settings().registryKey(ENDER_FENCE_GATE_ITEM_KEY)
        )
);

// DARK BUTTON
private static final Identifier ENDER_BUTTON_ID =
        Identifier.of(MOD_ID, "ender_button");

public static final RegistryKey<Block> ENDER_BUTTON_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_BUTTON_ID);

public static final RegistryKey<Item> ENDER_BUTTON_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_BUTTON_ID);

public static final Block ENDER_BUTTON = Registry.register(
        Registries.BLOCK,
        ENDER_BUTTON_ID,
        new ButtonBlock(
                BlockSetType.OAK,
                30,
                AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)
                        .registryKey(ENDER_BUTTON_KEY)
        )
);

public static final Item ENDER_BUTTON_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_BUTTON_ID,
        new BlockItem(
                ENDER_BUTTON,
                new Item.Settings().registryKey(ENDER_BUTTON_ITEM_KEY)
        )
);

// DARK PRESSURE PLATE
private static final Identifier ENDER_PRESSURE_PLATE_ID =
        Identifier.of(MOD_ID, "ender_pressure_plate");

public static final RegistryKey<Block> ENDER_PRESSURE_PLATE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_PRESSURE_PLATE_ID);

public static final RegistryKey<Item> ENDER_PRESSURE_PLATE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_PRESSURE_PLATE_ID);

public static final Block ENDER_PRESSURE_PLATE = Registry.register(
        Registries.BLOCK,
        ENDER_PRESSURE_PLATE_ID,
        new PressurePlateBlock(
                BlockSetType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)
                        .registryKey(ENDER_PRESSURE_PLATE_KEY)
        )
);

public static final Item ENDER_PRESSURE_PLATE_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_PRESSURE_PLATE_ID,
        new BlockItem(
                ENDER_PRESSURE_PLATE,
                new Item.Settings().registryKey(ENDER_PRESSURE_PLATE_ITEM_KEY)
        )
);

// DARK STAIRS
private static final Identifier ENDER_STAIRS_ID =
        Identifier.of(MOD_ID, "ender_stairs");

public static final RegistryKey<Block> ENDER_STAIRS_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_STAIRS_ID);

public static final RegistryKey<Item> ENDER_STAIRS_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_STAIRS_ID);

public static final Block ENDER_STAIRS = Registry.register(
        Registries.BLOCK,
        ENDER_STAIRS_ID,
        new StairsBlock(
                ENDER_PLANKS.getDefaultState(),
                AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)
                        .registryKey(ENDER_STAIRS_KEY)
        )
);

public static final Item ENDER_STAIRS_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_STAIRS_ID,
        new BlockItem(
                ENDER_STAIRS,
                new Item.Settings().registryKey(ENDER_STAIRS_ITEM_KEY)
        )
);

// DARK WOOD TYPE
public static final WoodType ENDER_WOOD_TYPE =
        new WoodTypeBuilder().register(
                Identifier.of(MOD_ID, "ender"),
                BlockSetType.OAK
        );

 // DARK SIGN
private static final Identifier ENDER_SIGN_ID =
        Identifier.of(MOD_ID, "ender_sign");

public static final RegistryKey<Block> ENDER_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_SIGN_ID);

public static final Block ENDER_SIGN = Registry.register(
        Registries.BLOCK,
        ENDER_SIGN_ID,
        new SignBlock(
                ENDER_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_SIGN)
                        .registryKey(ENDER_SIGN_KEY)
        )
);

// DARK WALL SIGN
private static final Identifier ENDER_WALL_SIGN_ID =
        Identifier.of(MOD_ID, "ender_wall_sign");

public static final RegistryKey<Block> ENDER_WALL_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_WALL_SIGN_ID);

public static final Block ENDER_WALL_SIGN = Registry.register(
        Registries.BLOCK,
        ENDER_WALL_SIGN_ID,
        new WallSignBlock(
                ENDER_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)
                        .registryKey(ENDER_WALL_SIGN_KEY)
        )
);

// DARK SIGN ITEM
private static final Identifier ENDER_SIGN_ITEM_ID =
        Identifier.of(MOD_ID, "ender_sign");

public static final RegistryKey<Item> ENDER_SIGN_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_SIGN_ITEM_ID);

public static final Item ENDER_SIGN_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_SIGN_ITEM_ID,
        new SignItem(
                ENDER_SIGN,
                ENDER_WALL_SIGN,
                new Item.Settings().registryKey(ENDER_SIGN_ITEM_KEY)
        )
);


// DARK TRAPDOOR
private static final Identifier ENDER_TRAPDOOR_ID =
        Identifier.of(MOD_ID, "ender_trapdoor");

public static final RegistryKey<Block> ENDER_TRAPDOOR_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_TRAPDOOR_ID);

public static final RegistryKey<Item> ENDER_TRAPDOOR_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_TRAPDOOR_ID);

public static final Block ENDER_TRAPDOOR = Registry.register(
        Registries.BLOCK,
        ENDER_TRAPDOOR_ID,
        new TrapdoorBlock(
                BlockSetType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)
                        .registryKey(ENDER_TRAPDOOR_KEY)
        )
);

public static final Item ENDER_TRAPDOOR_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_TRAPDOOR_ID,
        new BlockItem(
                ENDER_TRAPDOOR,
                new Item.Settings().registryKey(ENDER_TRAPDOOR_ITEM_KEY)
        )
);

// DARK HANGING SIGN
private static final Identifier ENDER_HANGING_SIGN_ID =
        Identifier.of(MOD_ID, "ender_hanging_sign");

public static final RegistryKey<Block> ENDER_HANGING_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_HANGING_SIGN_ID);

public static final RegistryKey<Item> ENDER_HANGING_SIGN_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_HANGING_SIGN_ID);

public static final Block ENDER_HANGING_SIGN = Registry.register(
        Registries.BLOCK,
        ENDER_HANGING_SIGN_ID,
        new HangingSignBlock(
                ENDER_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)
                        .registryKey(ENDER_HANGING_SIGN_KEY)
        )
);

// DARK WALL HANGING SIGN
private static final Identifier ENDER_WALL_HANGING_SIGN_ID =
        Identifier.of(MOD_ID, "ender_wall_hanging_sign");

public static final RegistryKey<Block> ENDER_WALL_HANGING_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_WALL_HANGING_SIGN_ID);

public static final Block ENDER_WALL_HANGING_SIGN = Registry.register(
        Registries.BLOCK,
        ENDER_WALL_HANGING_SIGN_ID,
        new WallHangingSignBlock(
                ENDER_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN)
                        .registryKey(ENDER_WALL_HANGING_SIGN_KEY)
        )
);

// DARK HANGING SIGN ITEM
public static final Item ENDER_HANGING_SIGN_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_HANGING_SIGN_ID,
        new HangingSignItem(
                ENDER_HANGING_SIGN,
                ENDER_WALL_HANGING_SIGN,
                new Item.Settings().registryKey(ENDER_HANGING_SIGN_ITEM_KEY)
        )
);

// DARK DOOR
private static final Identifier ENDER_DOOR_ID =
        Identifier.of(MOD_ID, "ender_door");

public static final RegistryKey<Block> ENDER_DOOR_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_DOOR_ID);

public static final RegistryKey<Item> ENDER_DOOR_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_DOOR_ID);

public static final Block ENDER_DOOR = Registry.register(
        Registries.BLOCK,
        ENDER_DOOR_ID,
        new DoorBlock(
                BlockSetType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_DOOR)
                        .registryKey(ENDER_DOOR_KEY)
        )
);

public static final Item ENDER_DOOR_ITEM = Registry.register(
        Registries.ITEM,
        ENDER_DOOR_ID,
        new BlockItem(
                ENDER_DOOR,
                new Item.Settings().registryKey(ENDER_DOOR_ITEM_KEY)
        )
);

// DARK BOAT

private static final Identifier ENDER_BOAT_ID =
        Identifier.of(MOD_ID, "ender_boat");

public static final RegistryKey<EntityType<?>> ENDER_BOAT_ENTITY_KEY =
        RegistryKey.of(RegistryKeys.ENTITY_TYPE, ENDER_BOAT_ID);

public static final EntityType<BoatEntity> ENDER_BOAT_ENTITY = Registry.register(
        Registries.ENTITY_TYPE,
        ENDER_BOAT_ID,
        EntityType.Builder.<BoatEntity>create(
                (type, world) -> new BoatEntity(
                        type,
                        world,
                        Enderwilds::getEnderBoatItem
                ),
                SpawnGroup.MISC
        )
                .dimensions(1.375F, 0.5625F)
                .maxTrackingRange(10)
                .trackingTickInterval(3)
                .build(ENDER_BOAT_ENTITY_KEY)
);

public static final RegistryKey<Item> ENDER_BOAT_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_BOAT_ID);

public static final Item ENDER_BOAT = Registry.register(
        Registries.ITEM,
        ENDER_BOAT_ID,
        new BoatItem(
                ENDER_BOAT_ENTITY,
                new Item.Settings().registryKey(ENDER_BOAT_ITEM_KEY)
        )
);

private static Item getEnderBoatItem() {
    return ENDER_BOAT;
}

// DARK CHEST BOAT

private static final Identifier ENDER_CHEST_BOAT_ID =
        Identifier.of(MOD_ID, "ender_chest_boat");

public static final RegistryKey<EntityType<?>> ENDER_CHEST_BOAT_ENTITY_KEY =
        RegistryKey.of(RegistryKeys.ENTITY_TYPE, ENDER_CHEST_BOAT_ID);

public static final EntityType<ChestBoatEntity> ENDER_CHEST_BOAT_ENTITY = Registry.register(
        Registries.ENTITY_TYPE,
        ENDER_CHEST_BOAT_ID,
        EntityType.Builder.<ChestBoatEntity>create(
                (type, world) -> new ChestBoatEntity(
                        type,
                        world,
                        Enderwilds::getEnderChestBoatItem
                ),
                SpawnGroup.MISC
        )
                .dimensions(1.375F, 0.5625F)
                .maxTrackingRange(10)
                .trackingTickInterval(3)
                .build(ENDER_CHEST_BOAT_ENTITY_KEY)
);

public static final RegistryKey<Item> ENDER_CHEST_BOAT_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_CHEST_BOAT_ID);

public static final Item ENDER_CHEST_BOAT = Registry.register(
        Registries.ITEM,
        ENDER_CHEST_BOAT_ID,
        new BoatItem(
                ENDER_CHEST_BOAT_ENTITY,
                new Item.Settings().registryKey(ENDER_CHEST_BOAT_ITEM_KEY)
        )
);

private static Item getEnderChestBoatItem() {
    return ENDER_CHEST_BOAT;
}

// DARK TWISTING VINES

public static final EnderTwistingVinesPlantBlock ENDER_TWISTING_VINES_PLANT =
        Registry.register(
                Registries.BLOCK,
                Identifier.of("enderwilds", "ender_twisting_vines_plant"),
                new EnderTwistingVinesPlantBlock(
                        Block.Settings.copy(Blocks.TWISTING_VINES_PLANT)
                                .registryKey(RegistryKey.of(
                                        RegistryKeys.BLOCK,
                                        Identifier.of("enderwilds", "ender_twisting_vines_plant")
                                ))
                )
        );

public static final EnderTwistingVinesBlock ENDER_TWISTING_VINES =
        Registry.register(
                Registries.BLOCK,
                Identifier.of("enderwilds", "ender_twisting_vines"),
                new EnderTwistingVinesBlock(
                        Block.Settings.copy(Blocks.TWISTING_VINES)
                                .registryKey(RegistryKey.of(
                                        RegistryKeys.BLOCK,
                                        Identifier.of("enderwilds", "ender_twisting_vines")
                                ))
                )
        );

public static final BlockItem ENDER_TWISTING_VINES_ITEM =
        Registry.register(
                Registries.ITEM,
                Identifier.of("enderwilds", "ender_twisting_vines"),
                new BlockItem(
                        ENDER_TWISTING_VINES,
                        new Item.Settings().registryKey(RegistryKey.of(
                                RegistryKeys.ITEM,
                                Identifier.of("enderwilds", "ender_twisting_vines")
                        ))
                )
        );

// DARK WEEPING VINES

private static final Identifier ENDER_WEEPING_VINES_ID =
        Identifier.of(MOD_ID, "ender_weeping_vines");

private static final Identifier ENDER_WEEPING_VINES_PLANT_ID =
        Identifier.of(MOD_ID, "ender_weeping_vines_plant");

private static final RegistryKey<Block> ENDER_WEEPING_VINES_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_WEEPING_VINES_ID);

private static final RegistryKey<Block> ENDER_WEEPING_VINES_PLANT_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ENDER_WEEPING_VINES_PLANT_ID);

private static final RegistryKey<Item> ENDER_WEEPING_VINES_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ENDER_WEEPING_VINES_ID);

public static final EnderWeepingVinesPlantBlock ENDER_WEEPING_VINES_PLANT =
        Registry.register(
                Registries.BLOCK,
                ENDER_WEEPING_VINES_PLANT_ID,
                new EnderWeepingVinesPlantBlock(
                        AbstractBlock.Settings.copy(Blocks.WEEPING_VINES_PLANT)
                                .registryKey(ENDER_WEEPING_VINES_PLANT_KEY)
                )
        );

public static final EnderWeepingVinesBlock ENDER_WEEPING_VINES =
        Registry.register(
                Registries.BLOCK,
                ENDER_WEEPING_VINES_ID,
                new EnderWeepingVinesBlock(
                        AbstractBlock.Settings.copy(Blocks.WEEPING_VINES)
                                .registryKey(ENDER_WEEPING_VINES_KEY)
                )
        );

public static final Item ENDER_WEEPING_VINES_ITEM =
        Registry.register(
                Registries.ITEM,
                ENDER_WEEPING_VINES_ID,
                new BlockItem(
                        ENDER_WEEPING_VINES,
                        new Item.Settings()
                                .registryKey(ENDER_WEEPING_VINES_ITEM_KEY)
                )
        );



    // ASHENWOOD - osobny zestaw bloków, przedmiotów i pojazdów

    // CIEMNE NYLIUM

    private static final Identifier ASHEN_NYLIUM_ID =
            Identifier.of(MOD_ID, "ashen_nylium");

    public static final RegistryKey<Block> ASHEN_NYLIUM_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ASHEN_NYLIUM_ID);

    public static final RegistryKey<Item> ASHEN_NYLIUM_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ASHEN_NYLIUM_ID);

    public static final Block ASHEN_NYLIUM = Registry.register(
            Registries.BLOCK,
            ASHEN_NYLIUM_ID,
            new AshenNyliumBlock(AbstractBlock.Settings.copy(Blocks.END_STONE)
                    .ticksRandomly()
                    .strength(1.5F, 6.0F)
                    .requiresTool()
                    .registryKey(ASHEN_NYLIUM_KEY)
                    .mapColor(MapColor.PURPLE))
    );

    public static final Item ASHEN_NYLIUM_ITEM = Registry.register(
            Registries.ITEM,
            ASHEN_NYLIUM_ID,
            new BlockItem(ASHEN_NYLIUM, new Item.Settings()
                    .registryKey(ASHEN_NYLIUM_ITEM_KEY))
    );

    // MROCZNE KORZENIE

    private static final Identifier ASHEN_ROOTS_ID =
            Identifier.of(MOD_ID, "ashen_roots");

    public static final RegistryKey<Block> ASHEN_ROOTS_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ASHEN_ROOTS_ID);

    public static final RegistryKey<Item> ASHEN_ROOTS_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ASHEN_ROOTS_ID);

    public static final Block ASHEN_ROOTS = Registry.register(
            Registries.BLOCK,
            ASHEN_ROOTS_ID,
            new AshenRootsBlock(
                    AbstractBlock.Settings.copy(Blocks.WARPED_ROOTS)
                            .nonOpaque()
                            .registryKey(ASHEN_ROOTS_KEY)
                            .mapColor(MapColor.PURPLE)
            )
    );

    public static final Item ASHEN_ROOTS_ITEM = Registry.register(
            Registries.ITEM,
            ASHEN_ROOTS_ID,
            new BlockItem(ASHEN_ROOTS, new Item.Settings()
                    .registryKey(ASHEN_ROOTS_ITEM_KEY))
    );

    // DARK SPROUTS

    private static final Identifier ASHEN_SPROUTS_ID =
            Identifier.of(MOD_ID, "ashen_sprouts");

    public static final RegistryKey<Block> ASHEN_SPROUTS_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ASHEN_SPROUTS_ID);

    public static final RegistryKey<Item> ASHEN_SPROUTS_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ASHEN_SPROUTS_ID);

    public static final Block ASHEN_SPROUTS = Registry.register(
            Registries.BLOCK,
            ASHEN_SPROUTS_ID,
            new AshenSproutsBlock(
                    AbstractBlock.Settings.copy(Blocks.NETHER_SPROUTS)
                            .nonOpaque()
                            .registryKey(ASHEN_SPROUTS_KEY)
            )
    );

    public static final Item ASHEN_SPROUTS_ITEM = Registry.register(
            Registries.ITEM,
            ASHEN_SPROUTS_ID,
            new BlockItem(ASHEN_SPROUTS, new Item.Settings()
                    .registryKey(ASHEN_SPROUTS_ITEM_KEY))
    );

    // DARK FUNGUS

    private static final Identifier ASHEN_FUNGUS_ID =
            Identifier.of(MOD_ID, "ashen_fungus");

    public static final RegistryKey<Block> ASHEN_FUNGUS_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ASHEN_FUNGUS_ID);

    public static final RegistryKey<Item> ASHEN_FUNGUS_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ASHEN_FUNGUS_ID);

    public static final Block ASHEN_FUNGUS = Registry.register(
            Registries.BLOCK,
            ASHEN_FUNGUS_ID,
            new AshenFungusBlock(
                    AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM)
                            .nonOpaque()
                            .registryKey(ASHEN_FUNGUS_KEY)
            )
    );

    public static final Item ASHEN_FUNGUS_ITEM = Registry.register(
            Registries.ITEM,
            ASHEN_FUNGUS_ID,
            new BlockItem(ASHEN_FUNGUS, new Item.Settings()
                    .registryKey(ASHEN_FUNGUS_ITEM_KEY))
    );

    // DARK STEM

    private static final Identifier ASHEN_STEM_ID =
            Identifier.of(MOD_ID, "ashen_stem");

    public static final RegistryKey<Block> ASHEN_STEM_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ASHEN_STEM_ID);

    public static final RegistryKey<Item> ASHEN_STEM_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ASHEN_STEM_ID);

    public static final Block ASHEN_STEM = Registry.register(
            Registries.BLOCK,
            ASHEN_STEM_ID,
            new EnderStemBlock(
                    AbstractBlock.Settings.copy(Blocks.CRIMSON_STEM)
                            .registryKey(ASHEN_STEM_KEY)
            )
    );

    public static final Item ASHEN_STEM_ITEM = Registry.register(
            Registries.ITEM,
            ASHEN_STEM_ID,
            new BlockItem(ASHEN_STEM, new Item.Settings()
                    .registryKey(ASHEN_STEM_ITEM_KEY))
    );


    // DARK WART BLOCK

    private static final Identifier ASHEN_WART_BLOCK_ID =
            Identifier.of(MOD_ID, "ashen_wart_block");

    public static final RegistryKey<Block> ASHEN_WART_BLOCK_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ASHEN_WART_BLOCK_ID);

    public static final RegistryKey<Item> ASHEN_WART_BLOCK_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ASHEN_WART_BLOCK_ID);

    public static final Block ASHEN_WART_BLOCK = Registry.register(
            Registries.BLOCK,
            ASHEN_WART_BLOCK_ID,
            new Block(
                    AbstractBlock.Settings.copy(Blocks.WARPED_WART_BLOCK)
                            .registryKey(ASHEN_WART_BLOCK_KEY)
            )
    );

    public static final Item ASHEN_WART_BLOCK_ITEM = Registry.register(
            Registries.ITEM,
            ASHEN_WART_BLOCK_ID,
            new BlockItem(ASHEN_WART_BLOCK, new Item.Settings()
                    .registryKey(ASHEN_WART_BLOCK_ITEM_KEY))
    );

    // STRIPPED DARK STEM

private static final Identifier STRIPPED_ASHEN_STEM_ID =
        Identifier.of(MOD_ID, "stripped_ashen_stem");

public static final RegistryKey<Block> STRIPPED_ASHEN_STEM_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, STRIPPED_ASHEN_STEM_ID);

public static final RegistryKey<Item> STRIPPED_ASHEN_STEM_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, STRIPPED_ASHEN_STEM_ID);

public static final Block STRIPPED_ASHEN_STEM = Registry.register(
        Registries.BLOCK,
        STRIPPED_ASHEN_STEM_ID,
        new EnderStemBlock(
                AbstractBlock.Settings.copy(Blocks.STRIPPED_CRIMSON_STEM)
                        .registryKey(STRIPPED_ASHEN_STEM_KEY)
        )
);

public static final Item STRIPPED_ASHEN_STEM_ITEM = Registry.register(
        Registries.ITEM,
        STRIPPED_ASHEN_STEM_ID,
        new BlockItem(STRIPPED_ASHEN_STEM, new Item.Settings()
                .registryKey(STRIPPED_ASHEN_STEM_ITEM_KEY))
);


// DARK PLANKS

private static final Identifier ASHEN_PLANKS_ID =
        Identifier.of(MOD_ID, "ashen_planks");

public static final RegistryKey<Block> ASHEN_PLANKS_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_PLANKS_ID);

public static final RegistryKey<Item> ASHEN_PLANKS_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_PLANKS_ID);

public static final Block ASHEN_PLANKS = Registry.register(
        Registries.BLOCK,
        ASHEN_PLANKS_ID,
        new Block(
                AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
                        .registryKey(ASHEN_PLANKS_KEY)
        )
);

public static final Item ASHEN_PLANKS_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_PLANKS_ID,
        new BlockItem(ASHEN_PLANKS, new Item.Settings()
                .registryKey(ASHEN_PLANKS_ITEM_KEY))
);


// DARK HYPHAE

private static final Identifier ASHEN_HYPHAE_ID =
        Identifier.of(MOD_ID, "ashen_hyphae");

public static final RegistryKey<Block> ASHEN_HYPHAE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_HYPHAE_ID);

public static final RegistryKey<Item> ASHEN_HYPHAE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_HYPHAE_ID);

public static final Block ASHEN_HYPHAE = Registry.register(
        Registries.BLOCK,
        ASHEN_HYPHAE_ID,
        new PillarBlock(
                AbstractBlock.Settings.copy(Blocks.CRIMSON_HYPHAE)
                        .registryKey(ASHEN_HYPHAE_KEY)
        )
);

public static final Item ASHEN_HYPHAE_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_HYPHAE_ID,
        new BlockItem(ASHEN_HYPHAE, new Item.Settings()
                .registryKey(ASHEN_HYPHAE_ITEM_KEY))
);

// STRIPPED DARK HYPHAE

private static final Identifier STRIPPED_ASHEN_HYPHAE_ID =
        Identifier.of(MOD_ID, "stripped_ashen_hyphae");

public static final RegistryKey<Block> STRIPPED_ASHEN_HYPHAE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, STRIPPED_ASHEN_HYPHAE_ID);

public static final RegistryKey<Item> STRIPPED_ASHEN_HYPHAE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, STRIPPED_ASHEN_HYPHAE_ID);

public static final Block STRIPPED_ASHEN_HYPHAE = Registry.register(
        Registries.BLOCK,
        STRIPPED_ASHEN_HYPHAE_ID,
        new PillarBlock(
                AbstractBlock.Settings.copy(Blocks.STRIPPED_CRIMSON_HYPHAE)
                        .registryKey(STRIPPED_ASHEN_HYPHAE_KEY)
        )
);

public static final Item STRIPPED_ASHEN_HYPHAE_ITEM = Registry.register(
        Registries.ITEM,
        STRIPPED_ASHEN_HYPHAE_ID,
        new BlockItem(STRIPPED_ASHEN_HYPHAE, new Item.Settings()
                .registryKey(STRIPPED_ASHEN_HYPHAE_ITEM_KEY))
);

// DARK SLAB

private static final Identifier ASHEN_SLAB_ID =
        Identifier.of(MOD_ID, "ashen_slab");

public static final RegistryKey<Block> ASHEN_SLAB_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_SLAB_ID);

public static final RegistryKey<Item> ASHEN_SLAB_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_SLAB_ID);

public static final Block ASHEN_SLAB = Registry.register(
        Registries.BLOCK,
        ASHEN_SLAB_ID,
        new SlabBlock(
                AbstractBlock.Settings.copy(Blocks.OAK_SLAB)
                        .registryKey(ASHEN_SLAB_KEY)
        )
);

public static final Item ASHEN_SLAB_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_SLAB_ID,
        new BlockItem(
                ASHEN_SLAB,
                new Item.Settings().registryKey(ASHEN_SLAB_ITEM_KEY)
        )
);

// DARK FENCE

private static final Identifier ASHEN_FENCE_ID =
        Identifier.of(MOD_ID, "ashen_fence");

public static final RegistryKey<Block> ASHEN_FENCE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_FENCE_ID);

public static final RegistryKey<Item> ASHEN_FENCE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_FENCE_ID);

public static final Block ASHEN_FENCE = Registry.register(
        Registries.BLOCK,
        ASHEN_FENCE_ID,
        new FenceBlock(
                AbstractBlock.Settings.copy(Blocks.OAK_FENCE)
                        .registryKey(ASHEN_FENCE_KEY)
        )
);

public static final Item ASHEN_FENCE_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_FENCE_ID,
        new BlockItem(
                ASHEN_FENCE,
                new Item.Settings().registryKey(ASHEN_FENCE_ITEM_KEY)
        )
);

// DARK FENCE GATE

private static final Identifier ASHEN_FENCE_GATE_ID =
        Identifier.of(MOD_ID, "ashen_fence_gate");

public static final RegistryKey<Block> ASHEN_FENCE_GATE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_FENCE_GATE_ID);

public static final RegistryKey<Item> ASHEN_FENCE_GATE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_FENCE_GATE_ID);

public static final Block ASHEN_FENCE_GATE = Registry.register(
        Registries.BLOCK,
        ASHEN_FENCE_GATE_ID,
        new FenceGateBlock(
                WoodType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE)
                        .registryKey(ASHEN_FENCE_GATE_KEY)
        )
);

public static final Item ASHEN_FENCE_GATE_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_FENCE_GATE_ID,
        new BlockItem(
                ASHEN_FENCE_GATE,
                new Item.Settings().registryKey(ASHEN_FENCE_GATE_ITEM_KEY)
        )
);

// DARK BUTTON
private static final Identifier ASHEN_BUTTON_ID =
        Identifier.of(MOD_ID, "ashen_button");

public static final RegistryKey<Block> ASHEN_BUTTON_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_BUTTON_ID);

public static final RegistryKey<Item> ASHEN_BUTTON_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_BUTTON_ID);

public static final Block ASHEN_BUTTON = Registry.register(
        Registries.BLOCK,
        ASHEN_BUTTON_ID,
        new ButtonBlock(
                BlockSetType.OAK,
                30,
                AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)
                        .registryKey(ASHEN_BUTTON_KEY)
        )
);

public static final Item ASHEN_BUTTON_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_BUTTON_ID,
        new BlockItem(
                ASHEN_BUTTON,
                new Item.Settings().registryKey(ASHEN_BUTTON_ITEM_KEY)
        )
);

// DARK PRESSURE PLATE
private static final Identifier ASHEN_PRESSURE_PLATE_ID =
        Identifier.of(MOD_ID, "ashen_pressure_plate");

public static final RegistryKey<Block> ASHEN_PRESSURE_PLATE_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_PRESSURE_PLATE_ID);

public static final RegistryKey<Item> ASHEN_PRESSURE_PLATE_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_PRESSURE_PLATE_ID);

public static final Block ASHEN_PRESSURE_PLATE = Registry.register(
        Registries.BLOCK,
        ASHEN_PRESSURE_PLATE_ID,
        new PressurePlateBlock(
                BlockSetType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)
                        .registryKey(ASHEN_PRESSURE_PLATE_KEY)
        )
);

public static final Item ASHEN_PRESSURE_PLATE_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_PRESSURE_PLATE_ID,
        new BlockItem(
                ASHEN_PRESSURE_PLATE,
                new Item.Settings().registryKey(ASHEN_PRESSURE_PLATE_ITEM_KEY)
        )
);

// DARK STAIRS
private static final Identifier ASHEN_STAIRS_ID =
        Identifier.of(MOD_ID, "ashen_stairs");

public static final RegistryKey<Block> ASHEN_STAIRS_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_STAIRS_ID);

public static final RegistryKey<Item> ASHEN_STAIRS_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_STAIRS_ID);

public static final Block ASHEN_STAIRS = Registry.register(
        Registries.BLOCK,
        ASHEN_STAIRS_ID,
        new StairsBlock(
                ASHEN_PLANKS.getDefaultState(),
                AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)
                        .registryKey(ASHEN_STAIRS_KEY)
        )
);

public static final Item ASHEN_STAIRS_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_STAIRS_ID,
        new BlockItem(
                ASHEN_STAIRS,
                new Item.Settings().registryKey(ASHEN_STAIRS_ITEM_KEY)
        )
);

// DARK WOOD TYPE
public static final WoodType ASHEN_WOOD_TYPE =
        new WoodTypeBuilder().register(
                Identifier.of(MOD_ID, "ashen"),
                BlockSetType.OAK
        );

 // DARK SIGN
private static final Identifier ASHEN_SIGN_ID =
        Identifier.of(MOD_ID, "ashen_sign");

public static final RegistryKey<Block> ASHEN_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_SIGN_ID);

public static final Block ASHEN_SIGN = Registry.register(
        Registries.BLOCK,
        ASHEN_SIGN_ID,
        new SignBlock(
                ASHEN_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_SIGN)
                        .registryKey(ASHEN_SIGN_KEY)
        )
);

// DARK WALL SIGN
private static final Identifier ASHEN_WALL_SIGN_ID =
        Identifier.of(MOD_ID, "ashen_wall_sign");

public static final RegistryKey<Block> ASHEN_WALL_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_WALL_SIGN_ID);

public static final Block ASHEN_WALL_SIGN = Registry.register(
        Registries.BLOCK,
        ASHEN_WALL_SIGN_ID,
        new WallSignBlock(
                ASHEN_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)
                        .registryKey(ASHEN_WALL_SIGN_KEY)
        )
);

// DARK SIGN ITEM
private static final Identifier ASHEN_SIGN_ITEM_ID =
        Identifier.of(MOD_ID, "ashen_sign");

public static final RegistryKey<Item> ASHEN_SIGN_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_SIGN_ITEM_ID);

public static final Item ASHEN_SIGN_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_SIGN_ITEM_ID,
        new SignItem(
                ASHEN_SIGN,
                ASHEN_WALL_SIGN,
                new Item.Settings().registryKey(ASHEN_SIGN_ITEM_KEY)
        )
);


// DARK TRAPDOOR
private static final Identifier ASHEN_TRAPDOOR_ID =
        Identifier.of(MOD_ID, "ashen_trapdoor");

public static final RegistryKey<Block> ASHEN_TRAPDOOR_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_TRAPDOOR_ID);

public static final RegistryKey<Item> ASHEN_TRAPDOOR_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_TRAPDOOR_ID);

public static final Block ASHEN_TRAPDOOR = Registry.register(
        Registries.BLOCK,
        ASHEN_TRAPDOOR_ID,
        new TrapdoorBlock(
                BlockSetType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)
                        .registryKey(ASHEN_TRAPDOOR_KEY)
        )
);

public static final Item ASHEN_TRAPDOOR_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_TRAPDOOR_ID,
        new BlockItem(
                ASHEN_TRAPDOOR,
                new Item.Settings().registryKey(ASHEN_TRAPDOOR_ITEM_KEY)
        )
);

// DARK HANGING SIGN
private static final Identifier ASHEN_HANGING_SIGN_ID =
        Identifier.of(MOD_ID, "ashen_hanging_sign");

public static final RegistryKey<Block> ASHEN_HANGING_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_HANGING_SIGN_ID);

public static final RegistryKey<Item> ASHEN_HANGING_SIGN_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_HANGING_SIGN_ID);

public static final Block ASHEN_HANGING_SIGN = Registry.register(
        Registries.BLOCK,
        ASHEN_HANGING_SIGN_ID,
        new HangingSignBlock(
                ASHEN_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN)
                        .registryKey(ASHEN_HANGING_SIGN_KEY)
        )
);

// DARK WALL HANGING SIGN
private static final Identifier ASHEN_WALL_HANGING_SIGN_ID =
        Identifier.of(MOD_ID, "ashen_wall_hanging_sign");

public static final RegistryKey<Block> ASHEN_WALL_HANGING_SIGN_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_WALL_HANGING_SIGN_ID);

public static final Block ASHEN_WALL_HANGING_SIGN = Registry.register(
        Registries.BLOCK,
        ASHEN_WALL_HANGING_SIGN_ID,
        new WallHangingSignBlock(
                ASHEN_WOOD_TYPE,
                AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN)
                        .registryKey(ASHEN_WALL_HANGING_SIGN_KEY)
        )
);

// DARK HANGING SIGN ITEM
public static final Item ASHEN_HANGING_SIGN_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_HANGING_SIGN_ID,
        new HangingSignItem(
                ASHEN_HANGING_SIGN,
                ASHEN_WALL_HANGING_SIGN,
                new Item.Settings().registryKey(ASHEN_HANGING_SIGN_ITEM_KEY)
        )
);

// DARK DOOR
private static final Identifier ASHEN_DOOR_ID =
        Identifier.of(MOD_ID, "ashen_door");

public static final RegistryKey<Block> ASHEN_DOOR_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_DOOR_ID);

public static final RegistryKey<Item> ASHEN_DOOR_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_DOOR_ID);

public static final Block ASHEN_DOOR = Registry.register(
        Registries.BLOCK,
        ASHEN_DOOR_ID,
        new DoorBlock(
                BlockSetType.OAK,
                AbstractBlock.Settings.copy(Blocks.OAK_DOOR)
                        .registryKey(ASHEN_DOOR_KEY)
        )
);

public static final Item ASHEN_DOOR_ITEM = Registry.register(
        Registries.ITEM,
        ASHEN_DOOR_ID,
        new BlockItem(
                ASHEN_DOOR,
                new Item.Settings().registryKey(ASHEN_DOOR_ITEM_KEY)
        )
);

// DARK BOAT

private static final Identifier ASHEN_BOAT_ID =
        Identifier.of(MOD_ID, "ashen_boat");

public static final RegistryKey<EntityType<?>> ASHEN_BOAT_ENTITY_KEY =
        RegistryKey.of(RegistryKeys.ENTITY_TYPE, ASHEN_BOAT_ID);

public static final EntityType<BoatEntity> ASHEN_BOAT_ENTITY = Registry.register(
        Registries.ENTITY_TYPE,
        ASHEN_BOAT_ID,
        EntityType.Builder.<BoatEntity>create(
                (type, world) -> new BoatEntity(
                        type,
                        world,
                        Enderwilds::getAshenBoatItem
                ),
                SpawnGroup.MISC
        )
                .dimensions(1.375F, 0.5625F)
                .maxTrackingRange(10)
                .trackingTickInterval(3)
                .build(ASHEN_BOAT_ENTITY_KEY)
);

public static final RegistryKey<Item> ASHEN_BOAT_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_BOAT_ID);

public static final Item ASHEN_BOAT = Registry.register(
        Registries.ITEM,
        ASHEN_BOAT_ID,
        new BoatItem(
                ASHEN_BOAT_ENTITY,
                new Item.Settings().registryKey(ASHEN_BOAT_ITEM_KEY)
        )
);

private static Item getAshenBoatItem() {
    return ASHEN_BOAT;
}

// DARK CHEST BOAT

private static final Identifier ASHEN_CHEST_BOAT_ID =
        Identifier.of(MOD_ID, "ashen_chest_boat");

public static final RegistryKey<EntityType<?>> ASHEN_CHEST_BOAT_ENTITY_KEY =
        RegistryKey.of(RegistryKeys.ENTITY_TYPE, ASHEN_CHEST_BOAT_ID);

public static final EntityType<ChestBoatEntity> ASHEN_CHEST_BOAT_ENTITY = Registry.register(
        Registries.ENTITY_TYPE,
        ASHEN_CHEST_BOAT_ID,
        EntityType.Builder.<ChestBoatEntity>create(
                (type, world) -> new ChestBoatEntity(
                        type,
                        world,
                        Enderwilds::getAshenChestBoatItem
                ),
                SpawnGroup.MISC
        )
                .dimensions(1.375F, 0.5625F)
                .maxTrackingRange(10)
                .trackingTickInterval(3)
                .build(ASHEN_CHEST_BOAT_ENTITY_KEY)
);

public static final RegistryKey<Item> ASHEN_CHEST_BOAT_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_CHEST_BOAT_ID);

public static final Item ASHEN_CHEST_BOAT = Registry.register(
        Registries.ITEM,
        ASHEN_CHEST_BOAT_ID,
        new BoatItem(
                ASHEN_CHEST_BOAT_ENTITY,
                new Item.Settings().registryKey(ASHEN_CHEST_BOAT_ITEM_KEY)
        )
);

private static Item getAshenChestBoatItem() {
    return ASHEN_CHEST_BOAT;
}

// DARK TWISTING VINES

public static final AshenTwistingVinesPlantBlock ASHEN_TWISTING_VINES_PLANT =
        Registry.register(
                Registries.BLOCK,
                Identifier.of("enderwilds", "ashen_twisting_vines_plant"),
                new AshenTwistingVinesPlantBlock(
                        Block.Settings.copy(Blocks.TWISTING_VINES_PLANT)
                                .registryKey(RegistryKey.of(
                                        RegistryKeys.BLOCK,
                                        Identifier.of("enderwilds", "ashen_twisting_vines_plant")
                                ))
                )
        );

public static final AshenTwistingVinesBlock ASHEN_TWISTING_VINES =
        Registry.register(
                Registries.BLOCK,
                Identifier.of("enderwilds", "ashen_twisting_vines"),
                new AshenTwistingVinesBlock(
                        Block.Settings.copy(Blocks.TWISTING_VINES)
                                .registryKey(RegistryKey.of(
                                        RegistryKeys.BLOCK,
                                        Identifier.of("enderwilds", "ashen_twisting_vines")
                                ))
                )
        );

public static final BlockItem ASHEN_TWISTING_VINES_ITEM =
        Registry.register(
                Registries.ITEM,
                Identifier.of("enderwilds", "ashen_twisting_vines"),
                new BlockItem(
                        ASHEN_TWISTING_VINES,
                        new Item.Settings().registryKey(RegistryKey.of(
                                RegistryKeys.ITEM,
                                Identifier.of("enderwilds", "ashen_twisting_vines")
                        ))
                )
        );

// DARK WEEPING VINES

private static final Identifier ASHEN_WEEPING_VINES_ID =
        Identifier.of(MOD_ID, "ashen_weeping_vines");

private static final Identifier ASHEN_WEEPING_VINES_PLANT_ID =
        Identifier.of(MOD_ID, "ashen_weeping_vines_plant");

private static final RegistryKey<Block> ASHEN_WEEPING_VINES_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_WEEPING_VINES_ID);

private static final RegistryKey<Block> ASHEN_WEEPING_VINES_PLANT_KEY =
        RegistryKey.of(RegistryKeys.BLOCK, ASHEN_WEEPING_VINES_PLANT_ID);

private static final RegistryKey<Item> ASHEN_WEEPING_VINES_ITEM_KEY =
        RegistryKey.of(RegistryKeys.ITEM, ASHEN_WEEPING_VINES_ID);

public static final AshenWeepingVinesPlantBlock ASHEN_WEEPING_VINES_PLANT =
        Registry.register(
                Registries.BLOCK,
                ASHEN_WEEPING_VINES_PLANT_ID,
                new AshenWeepingVinesPlantBlock(
                        AbstractBlock.Settings.copy(Blocks.WEEPING_VINES_PLANT)
                                .registryKey(ASHEN_WEEPING_VINES_PLANT_KEY)
                )
        );

public static final AshenWeepingVinesBlock ASHEN_WEEPING_VINES =
        Registry.register(
                Registries.BLOCK,
                ASHEN_WEEPING_VINES_ID,
                new AshenWeepingVinesBlock(
                        AbstractBlock.Settings.copy(Blocks.WEEPING_VINES)
                                .registryKey(ASHEN_WEEPING_VINES_KEY)
                )
        );

public static final Item ASHEN_WEEPING_VINES_ITEM =
        Registry.register(
                Registries.ITEM,
                ASHEN_WEEPING_VINES_ID,
                new BlockItem(
                        ASHEN_WEEPING_VINES,
                        new Item.Settings()
                                .registryKey(ASHEN_WEEPING_VINES_ITEM_KEY)
                )
        );




    // DARK END STONE

    private static final Identifier DARK_END_STONE_ID =
            Identifier.of(MOD_ID, "dark_end_stone");

    public static final RegistryKey<Block> DARK_END_STONE_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, DARK_END_STONE_ID);

    public static final RegistryKey<Item> DARK_END_STONE_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, DARK_END_STONE_ID);

    public static final Block DARK_END_STONE = Registry.register(
            Registries.BLOCK,
            DARK_END_STONE_ID,
            new Block(
                    AbstractBlock.Settings.copy(Blocks.END_STONE)
                            .registryKey(DARK_END_STONE_KEY)
                            .mapColor(MapColor.PURPLE)
            )
    );

    public static final Item DARK_END_STONE_ITEM = Registry.register(
            Registries.ITEM,
            DARK_END_STONE_ID,
            new BlockItem(DARK_END_STONE, new Item.Settings()
                    .registryKey(DARK_END_STONE_ITEM_KEY))
    );

    // ENDERIUM ORE AND SHARD

    private static final Identifier ENDERIUM_ORE_ID =
            Identifier.of(MOD_ID, "enderium_ore");

    public static final RegistryKey<Block> ENDERIUM_ORE_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ENDERIUM_ORE_ID);

    public static final RegistryKey<Item> ENDERIUM_ORE_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ENDERIUM_ORE_ID);

    public static final Block ENDERIUM_ORE = Registry.register(
            Registries.BLOCK,
            ENDERIUM_ORE_ID,
            new Block(
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE)
                            .strength(3.0F, 3.0F)
                            .requiresTool()
                            .mapColor(MapColor.PURPLE)
                            .registryKey(ENDERIUM_ORE_KEY)
            )
    );

    public static final Item ENDERIUM_ORE_ITEM = Registry.register(
            Registries.ITEM,
            ENDERIUM_ORE_ID,
            new BlockItem(ENDERIUM_ORE, new Item.Settings()
                    .registryKey(ENDERIUM_ORE_ITEM_KEY))
    );

    private static final Identifier ENDERIUM_SHARD_ID =
            Identifier.of(MOD_ID, "enderium_shard");

    public static final RegistryKey<Item> ENDERIUM_SHARD_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ENDERIUM_SHARD_ID);

    public static final Item ENDERIUM_SHARD = Registry.register(
            Registries.ITEM,
            ENDERIUM_SHARD_ID,
            new Item(new Item.Settings().registryKey(ENDERIUM_SHARD_KEY))
    );

    // ASHENIUM ORE AND SHARD

    // ASHENIUM ORE AND SHARD

    private static final Identifier ASHENIUM_ORE_ID =
            Identifier.of(MOD_ID, "ashenium_ore");

    public static final RegistryKey<Block> ASHENIUM_ORE_KEY =
            RegistryKey.of(RegistryKeys.BLOCK, ASHENIUM_ORE_ID);

    public static final RegistryKey<Item> ASHENIUM_ORE_ITEM_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ASHENIUM_ORE_ID);

    public static final Block ASHENIUM_ORE = Registry.register(
            Registries.BLOCK,
            ASHENIUM_ORE_ID,
            new Block(
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE)
                            .strength(3.0F, 3.0F)
                            .requiresTool()
                            .mapColor(MapColor.PURPLE)
                            .registryKey(ASHENIUM_ORE_KEY)
            )
    );

    public static final Item ASHENIUM_ORE_ITEM = Registry.register(
            Registries.ITEM,
            ASHENIUM_ORE_ID,
            new BlockItem(ASHENIUM_ORE, new Item.Settings()
                    .registryKey(ASHENIUM_ORE_ITEM_KEY))
    );

    private static final Identifier ASHENIUM_SHARD_ID =
            Identifier.of(MOD_ID, "ashenium_shard");

    public static final RegistryKey<Item> ASHENIUM_SHARD_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ASHENIUM_SHARD_ID);

    public static final Item ASHENIUM_SHARD = Registry.register(
            Registries.ITEM,
            ASHENIUM_SHARD_ID,
            new Item(new Item.Settings().registryKey(ASHENIUM_SHARD_KEY))
    );

    // ENDERITE INGOT

    private static final Identifier ENDERITE_INGOT_ID =
            Identifier.of(MOD_ID, "enderite_ingot");

    public static final RegistryKey<Item> ENDERITE_INGOT_KEY =
            RegistryKey.of(RegistryKeys.ITEM, ENDERITE_INGOT_ID);

    public static final Item ENDERITE_INGOT = Registry.register(
            Registries.ITEM,
            ENDERITE_INGOT_ID,
            new Item(new Item.Settings().registryKey(ENDERITE_INGOT_KEY))
    );




    // ENDERITE TOOLS AND ARMOR
    private static Item registerEnderiteGear(String name,
            java.util.function.Function<Item.Settings, Item.Settings> configure) {
        Identifier id = Identifier.of(MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings settings = configure.apply(new Item.Settings().registryKey(key)).repairable(ENDERITE_INGOT);
        return Registry.register(Registries.ITEM, id, new Item(settings));
    }

    public static final Item ENDERITE_SWORD = registerEnderiteGear(
            "enderite_sword", settings -> settings.sword(ToolMaterial.DIAMOND, 3.0F, -2.4F));
    public static final Item ENDERITE_PICKAXE = registerEnderiteGear(
            "enderite_pickaxe", settings -> settings.pickaxe(ToolMaterials.DIAMOND, 1.0F, -2.8F));
    public static final Item ENDERITE_AXE = registerEnderiteGear(
            "enderite_axe", settings -> settings.axe(ToolMaterials.DIAMOND, 6.0F, -3.1F));
    public static final Item ENDERITE_SHOVEL = registerEnderiteGear(
            "enderite_shovel", settings -> settings.shovel(ToolMaterials.DIAMOND, 1.5F, -3.0F));
    public static final Item ENDERITE_HOE = registerEnderiteGear(
            "enderite_hoe", settings -> settings.hoe(ToolMaterials.DIAMOND, -3.0F, 0.0F));

    public static final Item ENDERITE_HELMET = registerEnderiteGear(
            "enderite_helmet", settings -> settings.armor(ArmorMaterials.DIAMOND, EquipmentType.HELMET));
    public static final Item ENDERITE_CHESTPLATE = registerEnderiteGear(
            "enderite_chestplate", settings -> settings.armor(ArmorMaterials.DIAMOND, EquipmentType.CHESTPLATE));
    public static final Item ENDERITE_LEGGINGS = registerEnderiteGear(
            "enderite_leggings", settings -> settings.armor(ArmorMaterials.DIAMOND, EquipmentType.LEGGINGS));
    public static final Item ENDERITE_BOOTS = registerEnderiteGear(
            "enderite_boots", settings -> settings.armor(ArmorMaterials.DIAMOND, EquipmentType.BOOTS));

    @Override
public void onInitialize() {

    pl.enderwilds.worldgen.EnderVinesFeatures.register();

    // Okorowywanie Mrocznego Trzonu
    StrippableBlockRegistry.register(
            ENDER_STEM,
            STRIPPED_ENDER_STEM
    );

    // Okorowywanie Mrocznych Strzępków
    StrippableBlockRegistry.register(
            ENDER_HYPHAE,
            STRIPPED_ENDER_HYPHAE
    );

    BlockEntityType.SIGN.addSupportedBlock(ENDER_SIGN);
    BlockEntityType.SIGN.addSupportedBlock(ENDER_WALL_SIGN);

    BlockEntityType.HANGING_SIGN.addSupportedBlock(ENDER_HANGING_SIGN);
    BlockEntityType.HANGING_SIGN.addSupportedBlock(ENDER_WALL_HANGING_SIGN);

    StrippableBlockRegistry.register(ASHEN_STEM, STRIPPED_ASHEN_STEM);
    StrippableBlockRegistry.register(ASHEN_HYPHAE, STRIPPED_ASHEN_HYPHAE);
    BlockEntityType.SIGN.addSupportedBlock(ASHEN_SIGN);
    BlockEntityType.SIGN.addSupportedBlock(ASHEN_WALL_SIGN);
    BlockEntityType.HANGING_SIGN.addSupportedBlock(ASHEN_HANGING_SIGN);
    BlockEntityType.HANGING_SIGN.addSupportedBlock(ASHEN_WALL_HANGING_SIGN);

    // Wszystkie przedmioty moda w jednej zakładce kreatywnej
    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.item.ItemGroups.BUILDING_BLOCKS
    ).register(entries -> {
        entries.add(ENDER_NYLIUM_ITEM);
        entries.add(ENDER_ROOTS_ITEM);
        entries.add(ENDER_SPROUTS_ITEM);
        entries.add(ENDER_FUNGUS_ITEM);
        entries.add(ENDER_STEM_ITEM);
        entries.add(ENDER_WART_BLOCK_ITEM);
        entries.add(STRIPPED_ENDER_STEM_ITEM);
        entries.add(ENDER_PLANKS_ITEM);
        entries.add(ENDER_HYPHAE_ITEM);
        entries.add(STRIPPED_ENDER_HYPHAE_ITEM);
        entries.add(ENDER_SLAB_ITEM);
        entries.add(ENDER_FENCE_ITEM);
        entries.add(ENDER_FENCE_GATE_ITEM);
        entries.add(ENDER_BUTTON_ITEM);
        entries.add(ENDER_PRESSURE_PLATE_ITEM);
        entries.add(ENDER_STAIRS_ITEM);
        entries.add(ENDER_SIGN_ITEM);
        entries.add(ENDER_TRAPDOOR_ITEM);
        entries.add(ENDER_HANGING_SIGN_ITEM);
        entries.add(ENDER_DOOR_ITEM);
        entries.add(ENDER_BOAT);
        entries.add(ENDER_CHEST_BOAT);
        entries.add(ENDER_TWISTING_VINES_ITEM);
        entries.add(ENDER_WEEPING_VINES_ITEM);

        entries.add(ASHEN_NYLIUM_ITEM);
        entries.add(ASHEN_ROOTS_ITEM);
        entries.add(ASHEN_SPROUTS_ITEM);
        entries.add(ASHEN_FUNGUS_ITEM);
        entries.add(ASHEN_STEM_ITEM);
        entries.add(ASHEN_WART_BLOCK_ITEM);
        entries.add(STRIPPED_ASHEN_STEM_ITEM);
        entries.add(ASHEN_PLANKS_ITEM);
        entries.add(ASHEN_HYPHAE_ITEM);
        entries.add(STRIPPED_ASHEN_HYPHAE_ITEM);
        entries.add(ASHEN_SLAB_ITEM);
        entries.add(ASHEN_FENCE_ITEM);
        entries.add(ASHEN_FENCE_GATE_ITEM);
        entries.add(ASHEN_BUTTON_ITEM);
        entries.add(ASHEN_PRESSURE_PLATE_ITEM);
        entries.add(ASHEN_STAIRS_ITEM);
        entries.add(ASHEN_SIGN_ITEM);
        entries.add(ASHEN_TRAPDOOR_ITEM);
        entries.add(ASHEN_HANGING_SIGN_ITEM);
        entries.add(ASHEN_DOOR_ITEM);
        entries.add(ASHEN_BOAT);
        entries.add(ASHEN_CHEST_BOAT);
        entries.add(ASHEN_TWISTING_VINES_ITEM);
        entries.add(ASHEN_WEEPING_VINES_ITEM);

        entries.add(DARK_END_STONE_ITEM);
        entries.add(ENDERIUM_ORE_ITEM);
        entries.add(ENDERIUM_SHARD);
        entries.add(ASHENIUM_ORE_ITEM);
        entries.add(ASHENIUM_SHARD);
        entries.add(ENDERITE_INGOT);
    });


    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.item.ItemGroups.TOOLS
    ).register(entries -> {
        entries.add(ENDERITE_PICKAXE);
        entries.add(ENDERITE_AXE);
        entries.add(ENDERITE_SHOVEL);
        entries.add(ENDERITE_HOE);
    });

    net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.modifyEntriesEvent(
            net.minecraft.item.ItemGroups.COMBAT
    ).register(entries -> {
        entries.add(ENDERITE_SWORD);
        entries.add(ENDERITE_HELMET);
        entries.add(ENDERITE_CHESTPLATE);
        entries.add(ENDERITE_LEGGINGS);
        entries.add(ENDERITE_BOOTS);
    });

}
}