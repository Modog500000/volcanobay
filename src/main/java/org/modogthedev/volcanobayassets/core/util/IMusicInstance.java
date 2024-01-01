package org.modogthedev.volcanobayassets.core.util;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;

public interface IMusicInstance extends TickableSoundInstance {

    default void setVolume(float data) {}
}
