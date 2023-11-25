package org.modogthedev.volcanobay.core.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import org.modogthedev.volcanobay.Volcanobay;
import org.modogthedev.volcanobay.core.networking.packet.StealthSyncDataS2CPacket;

public class ModMessages {
    public static final Identifier STEALTH_SYNC_ID = new Identifier(Volcanobay.MOD_ID, "stealth_sync");

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(STEALTH_SYNC_ID, StealthSyncDataS2CPacket::receive);
    }
}
