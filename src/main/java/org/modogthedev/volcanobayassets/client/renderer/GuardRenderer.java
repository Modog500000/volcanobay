package org.modogthedev.volcanobayassets.client.renderer;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.client.models.GuardEntityModel;
import org.modogthedev.volcanobayassets.core.entities.GuardEntity;

public class GuardRenderer extends HumanoidMobRenderer<GuardEntity, GuardEntityModel<GuardEntity>> {
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation(VolcanobayAssets.MODID, "textures/entity/guard_1.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation(VolcanobayAssets.MODID, "textures/entity/guard_2.png");
    private static final ResourceLocation TEXTURE_3 = new ResourceLocation(VolcanobayAssets.MODID, "textures/entity/guard_3.png");
    private static final ResourceLocation TEXTURE_4 = new ResourceLocation(VolcanobayAssets.MODID, "textures/entity/guard_4.png");
    private static final ResourceLocation TEXTURE_5 = new ResourceLocation(VolcanobayAssets.MODID, "textures/entity/guard_5.png");

    public GuardRenderer(EntityRendererProvider.Context ctx) {
        this(ctx, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }
    public GuardRenderer(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
        super(p_174382_, new GuardEntityModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new GuardEntityModel(p_174382_.bakeLayer(p_174384_)), new GuardEntityModel(p_174382_.bakeLayer(p_174385_)), p_174382_.getModelManager()));
    }


    @Override
    public ResourceLocation getTextureLocation(GuardEntity me) {
        if (me.skin == 1) {
            return TEXTURE_1;
        } else if (me.skin == 2) {
            return TEXTURE_2;
        } else if (me.skin == 3) {
            return TEXTURE_3;
        } else if (me.skin == 4) {
            return TEXTURE_4;
        } else {
            return TEXTURE_5;
        }
    }
}
