package org.modogthedev.volcanobay.core.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.modogthedev.volcanobay.core.networking.ModMessages;

public class StealthData {
    public static int addStealth(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int stealth = nbt.getInt("stealth");
        if(stealth + amount >= 10) {
            stealth = 10;
        } else {
            stealth += amount;
        }

        nbt.putInt("stealth", stealth);
        // sync the data
        return stealth;
    }

    public static int removeStealth(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int stealth = nbt.getInt("stealth");
        if(stealth - amount < 0) {
            stealth = 0;
        } else {
            stealth -= amount;
        }

        nbt.putInt("stealth", stealth);
        // syncThirst(stealth, (ServerPlayerEntity) player);
        return stealth;
    }
    public static void syncStealth(int thirst, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(thirst);
        ServerPlayNetworking.send(player, ModMessages.STEALTH_SYNC_ID, buffer);
    }
}
