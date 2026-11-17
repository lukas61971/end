package pl.enderwilds;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class EnderBoatEntityRenderer extends BoatEntityRenderer {

    private static final Identifier ENDER_BOAT_TEXTURE =
            Identifier.of("enderwilds", "textures/entity/boat/ender_boat.png");

    public EnderBoatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.OAK_BOAT);
    }

    @Override
protected RenderLayer getRenderLayer() {
    return RenderLayers.entityCutout(ENDER_BOAT_TEXTURE);
}
}