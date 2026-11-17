package pl.endusmroku;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.block.Blocks;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;


public class EndusMrokuClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(
                EndusMroku.MROCZNE_KORZENIE,
                BlockRenderLayer.CUTOUT
        );
        BlockRenderLayerMap.putBlock(
            EndusMroku.ENDER_SPROUTS,
            BlockRenderLayer.CUTOUT
        );
        BlockRenderLayerMap.putBlock(
            EndusMroku.ENDER_FUNGUS,
            BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
            EndusMroku.ENDER_TRAPDOOR,
            BlockRenderLayer.CUTOUT
        );

        EntityRendererRegistry.register(
            EndusMroku.ENDER_BOAT_ENTITY,
            EnderBoatEntityRenderer::new
        );

        EntityRendererRegistry.register(
            EndusMroku.ENDER_CHEST_BOAT_ENTITY,
            EnderChestBoatEntityRenderer::new
        );

        BlockRenderLayerMap.putBlock(
           EndusMroku.ENDER_TWISTING_VINES,
           BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
           EndusMroku.ENDER_TWISTING_VINES_PLANT,
           BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
           EndusMroku.ENDER_WEEPING_VINES,
           BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
           EndusMroku.ENDER_WEEPING_VINES_PLANT,
           BlockRenderLayer.CUTOUT
        );
    }
}