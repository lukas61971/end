package pl.endusmroku;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class DarkChestBoatEntityRenderer extends BoatEntityRenderer {

    private static final Identifier DARK_CHEST_BOAT_TEXTURE =
            Identifier.of("endusmroku", "textures/entity/boat/dark_chest_boat.png");

    public DarkChestBoatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.DARK_OAK_CHEST_BOAT);
    }

    @Override
    protected RenderLayer getRenderLayer() {
        return RenderLayers.entityCutout(DARK_CHEST_BOAT_TEXTURE);
    }
}