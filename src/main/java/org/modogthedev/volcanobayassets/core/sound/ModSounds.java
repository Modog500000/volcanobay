package org.modogthedev.volcanobayassets.core.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.modogthedev.volcanobayassets.VolcanobayAssets;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VolcanobayAssets.MODID);

    public static final RegistryObject<SoundEvent> MUSIC_INVOCATION = registerSoundEvents("music_invocation");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(VolcanobayAssets.MODID, name)));
    }
}