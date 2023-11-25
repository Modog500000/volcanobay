package org.modogthedev.volcanobay.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.modogthedev.volcanobay.core.networking.ModMessages;

public class VolcanobayClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        HudRenderCallback.EVENT.register(new StealthHudOverlay());
    }
}
