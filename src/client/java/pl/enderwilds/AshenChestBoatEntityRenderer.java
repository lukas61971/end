package pl.enderwilds;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class AshenChestBoatEntityRenderer extends BoatEntityRenderer {
    private static final Identifier ASHEN_CHEST_BOAT_TEXTURE =
            Identifier.of("enderwilds", "textures/entity/boat/ashen_chest_boat.png");

    public AshenChestBoatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, EntityModelLayers.OAK_CHEST_BOAT);
    }

    @Override
    protected RenderLayer getRenderLayer() {
        return RenderLayers.entityCutout(ASHEN_CHEST_BOAT_TEXTURE);
    }
}
