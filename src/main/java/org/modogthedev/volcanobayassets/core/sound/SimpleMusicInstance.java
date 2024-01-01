package org.modogthedev.volcanobayassets.core.sound;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.modogthedev.volcanobayassets.core.util.IMusicInstance;

@OnlyIn(Dist.CLIENT)
public class SimpleMusicInstance extends AbstractMusicInstance {
    public static SimpleMusicInstance forMusic(SoundEvent p_119746_) {
        return new SimpleMusicInstance(p_119746_, 1.0F, 1.0F, SoundInstance.createUnseededRandom());
    }
    private SimpleMusicInstance(SoundEvent soundEvent, float p_235089_, float p_235090_, RandomSource p_235091_) {
        this(soundEvent,p_235089_,p_235090_,p_235091_,true);
    }
    public SimpleMusicInstance(SoundEvent soundEvent, float p_235089_, float p_235090_, RandomSource p_235091_, boolean p_235098_) {
        super(soundEvent, SoundSource.MUSIC, p_235091_);
        this.volume = p_235089_;
        this.pitch = p_235090_;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.looping = true;
        this.delay = 0;
        this.attenuation = IMusicInstance.Attenuation.NONE;
        this.relative = false;
    }
}
