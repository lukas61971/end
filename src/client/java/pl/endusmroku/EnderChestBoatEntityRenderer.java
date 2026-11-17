package pl.endusmroku;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class EnderChestBoatEntityRenderer extends BoatEntityRenderer {

    private static final Identifier ENDER_CHEST_BOAT_TEXTURE =
            Identifier.of("endusmroku", "textures/entity/boat/ender_chest_boat.png");

    public EnderChestBoatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.ENDER_OAK_CHEST_BOAT);
    }

    @Override
    protected RenderLayer getRenderLayer() {
        return RenderLayers.entityCutout(ENDER_CHEST_BOAT_TEXTURE);
    }
}