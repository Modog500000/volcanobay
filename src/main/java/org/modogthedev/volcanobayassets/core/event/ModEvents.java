package org.modogthedev.volcanobayassets.core.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.core.networking.ModMessages;
import org.modogthedev.volcanobayassets.core.networking.packet.StealthSyncDataS2CPacket;
import org.modogthedev.volcanobayassets.core.util.PlayerStealthProvider;

public class ModEvents {

    @Mod.EventBusSubscriber(modid = VolcanobayAssets.MODID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            if(event.getObject() instanceof Player) {
                if(!event.getObject().getCapability(PlayerStealthProvider.PLAYER_STEALTH).isPresent()) {
                    event.addCapability(new ResourceLocation(VolcanobayAssets.MODID, "properties"), new PlayerStealthProvider());
                }
            }
        }
        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if(event.isWasDeath()) {
                event.getOriginal().getCapability(PlayerStealthProvider.PLAYER_STEALTH).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerStealthProvider.PLAYER_STEALTH).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }
        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if(!event.getLevel().isClientSide()) {
                if(event.getEntity() instanceof ServerPlayer player) {
                    player.getCapability(PlayerStealthProvider.PLAYER_STEALTH).ifPresent(stealth -> {
                        ModMessages.sendToPlayer(new StealthSyncDataS2CPacket(stealth.getStealth()), player);
                    });
                }
            }
        }
    }
}
