package org.modogthedev.volcanobayassets.core.util;


import net.minecraft.nbt.CompoundTag;

public class StealthData {
    private int stealth;
    private final int MIN_STEALTH = -100;
    private final int MAX_STEALTH = 100;

    public int getStealth() {
        return stealth;
    }

    public void addStealth(int add) {
        this.stealth = Math.min(stealth + add, MAX_STEALTH);
    }
    public void setStealth(int set) {
        this.stealth = set;
    }

    public void subStealth(int sub) {
        this.stealth = Math.max(stealth - sub, MIN_STEALTH);
    }

    public void copyFrom(StealthData source) {
        this.stealth = source.stealth;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("stealth", stealth);
    }

    public void loadNBTData(CompoundTag nbt) {
        stealth = nbt.getInt("stealth");
    }
}
