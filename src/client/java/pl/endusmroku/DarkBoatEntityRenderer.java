package pl.endusmroku;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class DarkBoatEntityRenderer extends BoatEntityRenderer {

    private static final Identifier DARK_BOAT_TEXTURE =
            Identifier.of("endusmroku", "textures/entity/boat/dark_boat.png");

    public DarkBoatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.DARK_OAK_BOAT);
    }

    @Override
protected RenderLayer getRenderLayer() {
    return RenderLayers.entityCutout(DARK_BOAT_TEXTURE);
}
}