package org.modogthedev.volcanobayassets.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.client.models.GuardEntityModel;
import org.modogthedev.volcanobayassets.core.entities.GuardEntity;

public class GuardRenderer extends MobRenderer<GuardEntity, GuardEntityModel<GuardEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(VolcanobayAssets.MODID, "textures/entity/guard_1.png");
    public GuardRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new GuardEntityModel<>(ctx.bakeLayer(GuardEntityModel.LAYER_LOCATION)), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(GuardEntity p_114482_) {
        return TEXTURE;
    }
}
