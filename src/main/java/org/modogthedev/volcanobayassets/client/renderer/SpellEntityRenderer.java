package org.modogthedev.volcanobayassets.client.renderer;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.client.models.SpellEntityModel;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class SpellEntityRenderer extends EntityRenderer<SpellEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(VolcanobayAssets.MODID, "textures/entities/spell_entity.png");

    public SpellEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        SpellEntityModel<SpellEntity> model = new SpellEntityModel<>(ctx.bakeLayer(SpellEntityModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(SpellEntity p_114482_) {
        return TEXTURE;
    }

}