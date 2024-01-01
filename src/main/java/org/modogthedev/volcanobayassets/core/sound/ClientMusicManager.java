package org.modogthedev.volcanobayassets.core.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.client.ClientStealthData;
import org.modogthedev.volcanobayassets.core.util.IMusicInstance;

import javax.annotation.Nullable;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class ClientMusicManager {
    private static final Minecraft minecraft = Minecraft.getInstance();
    @Nullable
    private static IMusicInstance currentMusic;
    private static int delay = 100;
    private static int timePlaying = 0;
    private static int waitForNext = 0;
    private static int fade = 0;
    private static int fadeTime = 0;
    private static final float volume = 1f;
    public static Track track;

    public static void clientMusicManager(TickEvent.ClientTickEvent event) { //Tick
        Player player = minecraft.player;
        if (currentMusic != null) {
            delay--;
            if (player != null) {
                if (player.getHealth() <= 0) {
                    beginFade(20);
                }
                if (track == Track.MENU) {
                    delay = 0;
                }
            }
            if (ClientStealthData.getPlayerStealth() < 0) {
                timePlaying++;
                delay = 200;
                if (timePlaying > 2000) {
                    delay = 0;
                }
                if (track != Track.BATTLE) {
                    delay = 0;
                    beginFade(40);
                }
            }
        } else { // Check to start playing sound
            if (minecraft.screen instanceof TitleScreen) {
                if (waitForNext <= 0) {
                        stopPlaying();
                        playSound(getSoundEvent(Track.MENU));
                        track = Track.MENU;
                        delay = (int) ((Math.random() + 1) * 2000);
                } else {
                    waitForNext--;
                }
            } else {
                if (waitForNext <= 0) {
                    if (ClientStealthData.getPlayerStealth() < 0) {
                        stopPlaying();
                        playSound(getSoundEvent(Track.BATTLE));
                        track = Track.BATTLE;
                        delay = (int) ((Math.random() + .5) * 1500);
                    }
                    if (Math.floor(Math.random()*200)==0) {
                        stopPlaying();
                        playSound(getSoundEvent(Track.EXPLORE));
                        track = Track.EXPLORE;
                        delay = (int) ((Math.random() + 1) * 2000);
                    }
                } else {
                    waitForNext--;
                }
            }
        }
        if (delay <= 0 && currentMusic != null && fade == 0) {
            beginFade(100);
            delay = 0;
        }
        if (fade > 0 ) {
            fadeOff();
        }
    }
    public static SoundEvent getSoundEvent(Track track) {
        switch (track) {
            case BATTLE -> { // Battle Tracks
                final int random = (int) Math.ceil(Math.random()*2);
                switch (random) {
                    case 1 -> {
                        return ModSounds.MUSIC_BATTLE_01.get();
                    }
                    case 2 -> {
                        return ModSounds.MUSIC_BATTLE_02.get();
                    }
                }
            }
            case EXPLORE -> { //Explore Tracks
                final int random = (int) Math.ceil(Math.random()*2);
                switch (random) {
                    case 1 -> {
                        return ModSounds.MUSIC_EXPLORE_01.get();
                    }
                    case 2 -> {
                        return ModSounds.MUSIC_EXPLORE_02.get();
                    }
                }
            }
            case MENU -> { //Explore Tracks
                final int random = (int) Math.ceil(Math.random()*1);
                switch (random) {
                    case 1 -> {
                        return ModSounds.MUSIC_EXPLORE_02.get();
                    }
                }
            }
        }
        return ModSounds.MUSIC_EXPLORE_01.get();
    }
    public static void playSound(SoundEvent sound) {
        timePlaying = 0;
        currentMusic = SimpleMusicInstance.forMusic(sound);
        minecraft.getSoundManager().play(currentMusic);
    }
    public static void fadeOff() {
        setVolume(fade);
        fade--;
        if (fade == 0) {
            stopPlaying();
        }
    }
    public static void beginFade(int ticksToFade) {
        if (fade <= 0) {
            fade = ticksToFade;
            fadeTime = ticksToFade;
        }
    }
    public static void stopPlaying() {
        if (currentMusic != null) {
            minecraft.getSoundManager().stop(currentMusic);
            currentMusic = null;
            track = Track.NONE;
            waitForNext = 100;
        }
    }
    public static void setVolume(float data) {
        if (currentMusic != null) {
        float newVolume = volume*((float) Math.max(fade, 1) /(float)Math.max(fadeTime, 1));
        currentMusic.setVolume(newVolume);
        }
    }
    public enum Track {
        BATTLE,
        EXPLORE,
        MENU,
        NONE
    }
}
