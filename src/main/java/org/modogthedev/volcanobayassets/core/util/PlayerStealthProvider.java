package org.modogthedev.volcanobayassets.core.util;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class PlayerStealthProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<StealthData> PLAYER_STEALTH = CapabilityManager.get(new CapabilityToken<StealthData>() { });

    private StealthData stealth = null;
    private final LazyOptional<StealthData> optional = LazyOptional.of(this::createPlayerStealth);

    private StealthData createPlayerStealth() {
        if(this.stealth == null) {
            this.stealth = new StealthData();
        }

        return this.stealth;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_STEALTH) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerStealth().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerStealth().loadNBTData(nbt);
    }
}