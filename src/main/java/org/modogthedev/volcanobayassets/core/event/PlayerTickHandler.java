package org.modogthedev.volcanobayassets.core.event;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import org.modogthedev.volcanobayassets.core.networking.ModMessages;
import org.modogthedev.volcanobayassets.core.networking.packet.StealthSyncDataS2CPacket;
import org.modogthedev.volcanobayassets.core.util.PlayerStealthProvider;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class PlayerTickHandler {
    static ArrayList<Vec3> pos = new ArrayList<Vec3>();
    static ArrayList<UUID> players = new ArrayList<UUID>();
    static int tickSinceSet = 0;

    public static void playerTickHandler(TickEvent.ServerTickEvent event) {
        tickSinceSet++;
        MinecraftServer server = event.getServer();
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (!players.contains(player.getUUID())) {
                players.add(player.getUUID());
                pos.add(player.position());
            }
            int index = players.indexOf(player.getUUID());
            boolean still = pos.get(index).subtract(player.position()).equals(Vec3.ZERO);
            if (tickSinceSet > 20) {
                pos.set(index, player.position());
            }
            if (player.isCrouching()) {
                if(new Random().nextFloat() <= 0.01f) {
                    addStealth(player,10);
                }
            }
            if (still) {
                if(new Random().nextFloat() <= 0.05f) {
                    addStealth(player,10);
                }
            }
            if (player.isSprinting() && !player.isCrouching()) {
                if(new Random().nextFloat() <= 0.05f) {
                    subStealth(player,10);
                }
            }
            if(new Random().nextFloat() <= 0.005f) {;
                addStealth(player,2);
            }
        }
        if (tickSinceSet > 20) {
            tickSinceSet = 0;
        }
    }

    public static void addStealth(ServerPlayer player, int amount) {
        player.getCapability(PlayerStealthProvider.PLAYER_STEALTH).ifPresent(stealthData -> {
                    stealthData.addStealth(amount);
            ModMessages.sendToPlayer(new StealthSyncDataS2CPacket(stealthData.getStealth()), player);
        });
    }
    public static void subStealth(ServerPlayer player, int amount) {
        player.getCapability(PlayerStealthProvider.PLAYER_STEALTH).ifPresent(stealthData -> {
            stealthData.subStealth(amount);
            ModMessages.sendToPlayer(new StealthSyncDataS2CPacket(stealthData.getStealth()), player);
        });
    }
}
