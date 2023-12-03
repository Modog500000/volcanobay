package org.modogthedev.volcanobayassets.core.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.client.StealthHudOverlay;
import org.modogthedev.volcanobayassets.client.models.SpellEntityModel;
import org.modogthedev.volcanobayassets.client.renderer.SpellEntityRenderer;
import org.modogthedev.volcanobayassets.core.ModEntities;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = VolcanobayAssets.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("stealth", StealthHudOverlay.HUD_STEALTH);
        }
        @SubscribeEvent
        public static void entityRenders(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.SPELL_ENTITY.get(), SpellEntityRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(SpellEntityModel.LAYER_LOCATION, SpellEntityModel::createBodyLayer);
        }
    }
}
