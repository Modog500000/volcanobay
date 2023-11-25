package org.modogthedev.volcanobay;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.jetbrains.annotations.NotNull;
import org.modogthedev.volcanobay.core.ModBlocks;
import org.modogthedev.volcanobay.core.ModItemGroup;
import org.modogthedev.volcanobay.core.ModItems;
import org.modogthedev.volcanobay.core.event.PlayerTickHandler;
import org.modogthedev.volcanobay.core.networking.ModMessages;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class Volcanobay implements ModInitializer {
    public static final String MOD_ID = "volcanobay";

    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModItemGroup.registerItemGroups();
        ModMessages.registerS2CPackets();

        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
    }
}
