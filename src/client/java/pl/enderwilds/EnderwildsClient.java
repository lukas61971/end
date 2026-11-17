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
                Enderwilds.MROCZNE_KORZENIE,
                BlockRenderLayer.CUTOUT
        );
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