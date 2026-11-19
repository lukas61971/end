package pl.enderwilds;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.block.Blocks;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;


public class EnderwildsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(
                Enderwilds.ENDER_ROOTS,
                BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(Enderwilds.ASHEN_ROOTS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Enderwilds.ASHEN_SPROUTS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Enderwilds.ASHEN_FUNGUS, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Enderwilds.ASHEN_TWISTING_VINES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Enderwilds.ASHEN_TWISTING_VINES_PLANT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Enderwilds.ASHEN_WEEPING_VINES, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Enderwilds.ASHEN_WEEPING_VINES_PLANT, BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(
            Enderwilds.ENDER_SPROUTS,
            BlockRenderLayer.CUTOUT
        );
        BlockRenderLayerMap.putBlock(
            Enderwilds.ENDER_FUNGUS,
            BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
            Enderwilds.ENDER_TRAPDOOR,
            BlockRenderLayer.CUTOUT
        );

        EntityRendererRegistry.register(
            Enderwilds.ENDER_BOAT_ENTITY,
            EnderBoatEntityRenderer::new
        );

        EntityRendererRegistry.register(
            Enderwilds.ENDER_CHEST_BOAT_ENTITY,
            EnderChestBoatEntityRenderer::new
        );

        EntityRendererRegistry.register(
            Enderwilds.ASHEN_BOAT_ENTITY,
            AshenBoatEntityRenderer::new
        );

        EntityRendererRegistry.register(
            Enderwilds.ASHEN_CHEST_BOAT_ENTITY,
            AshenChestBoatEntityRenderer::new
        );

        BlockRenderLayerMap.putBlock(
           Enderwilds.ENDER_TWISTING_VINES,
           BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
           Enderwilds.ENDER_TWISTING_VINES_PLANT,
           BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
           Enderwilds.ENDER_WEEPING_VINES,
           BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
           Enderwilds.ENDER_WEEPING_VINES_PLANT,
           BlockRenderLayer.CUTOUT
        );
    }
}