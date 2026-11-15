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
            EndusMroku.DARK_SPROUTS,
            BlockRenderLayer.CUTOUT
        );
        BlockRenderLayerMap.putBlock(
            EndusMroku.DARK_FUNGUS,
            BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
            EndusMroku.DARK_TRAPDOOR,
            BlockRenderLayer.CUTOUT
        );

        EntityRendererRegistry.register(
            EndusMroku.DARK_BOAT_ENTITY,
            DarkBoatEntityRenderer::new
        );

        EntityRendererRegistry.register(
            EndusMroku.DARK_CHEST_BOAT_ENTITY,
            DarkChestBoatEntityRenderer::new
        );

        BlockRenderLayerMap.putBlock(
           EndusMroku.DARK_TWISTING_VINES,
           BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
           EndusMroku.DARK_TWISTING_VINES_PLANT,
           BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
           EndusMroku.DARK_WEEPING_VINES,
           BlockRenderLayer.CUTOUT
        );

        BlockRenderLayerMap.putBlock(
           EndusMroku.DARK_WEEPING_VINES_PLANT,
           BlockRenderLayer.CUTOUT
        );
    }
}