
package pl.enderwilds;

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
import pl.enderwilds.block.EnderTwistingVinesBlock;
import pl.enderwilds.block.EnderTwistingVinesPlantBlock;
import pl.enderwilds.block.EnderWeepingVinesBlock;
import pl.enderwilds.block.EnderWeepingVinesPlantBlock;

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

}
}