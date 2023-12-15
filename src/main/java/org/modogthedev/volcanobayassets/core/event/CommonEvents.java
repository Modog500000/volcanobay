package org.modogthedev.volcanobayassets.core.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.core.ModEntities;
import org.modogthedev.volcanobayassets.core.entities.GuardEntity;

@Mod.EventBusSubscriber(modid = VolcanobayAssets.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {
    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GUARD.get(), GuardEntity.createAttributes().build());
    }
}
