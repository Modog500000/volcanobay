package org.modogthedev.volcanobayassets.core.networking.packet;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.client.ClientStealthData;

import java.util.function.Supplier;

public class StealthSyncDataS2CPacket {
    private final int stealth;

    public StealthSyncDataS2CPacket(int stealth) {
        this.stealth = stealth;
    }

    public StealthSyncDataS2CPacket(FriendlyByteBuf buf) {
        this.stealth = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(stealth);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientStealthData.set(stealth);
        });
        return true;
    }
}
