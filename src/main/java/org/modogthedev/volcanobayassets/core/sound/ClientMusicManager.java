package org.modogthedev.volcanobayassets.core.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.client.ClientStealthData;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class ClientMusicManager {
    private static final Minecraft minecraft = Minecraft.getInstance();
    @Nullable
    private static SoundInstance currentMusic;
    private static int delay = 100;
    private static int fade = 0;
    private static int fadeTime = 0;
    private static float volume = 100;
    public static String track;

    public static void clientMusicManager(TickEvent.ClientTickEvent event) { //Tick
        delay--;
        Player player = minecraft.player;
        if (currentMusic != null) {

        } else { // Check to start playing sound
            if (ClientStealthData.getPlayerStealth() < 0) {
                stopPlaying();
                playSound(ModSounds.MUSIC_INVOCATION.get());
                track = "battle";
            }
        }
        if (delay <= 0 && currentMusic != null && fade == 0) {
            beginFade(400);
            delay = 0;
        }
        if (fade > 0 ) {
            fadeOff(fade);
        }
    }
    public static void playSound(SoundEvent sound) {
        currentMusic = SimpleSoundInstance.forMusic(sound);
        minecraft.getSoundManager().play(currentMusic);
        delay = 400;
        VolcanobayAssets.LOGGER.info("Start playing!");
    }
    public static void fadeOff(int tick) {
        float newVolume = volume*((float) tick /fadeTime);
        VolcanobayAssets.LOGGER.info(String.valueOf(newVolume));
        minecraft.getSoundManager().updateSourceVolume(SoundSource.MUSIC, newVolume);
        fade--;
        if (fade == 0) {
            stopPlaying();
        }
    }
    public static void beginFade(int ticksToFade) {
        fade = ticksToFade;
        fadeTime = ticksToFade;
        VolcanobayAssets.LOGGER.info("Begin fade!");
    }
    public static void stopPlaying() {
        if (currentMusic != null) {
            minecraft.getSoundManager().stop(currentMusic);
            currentMusic = null;
            track = "none";
            VolcanobayAssets.LOGGER.info("Stop playing!");
        }
    }
}
