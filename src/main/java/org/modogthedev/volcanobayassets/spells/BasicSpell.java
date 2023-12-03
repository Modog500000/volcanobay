package org.modogthedev.volcanobayassets.spells;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class BasicSpell {
    static final FX fx_basic = FXHelper.getFX(new ResourceLocation("volcanobayassets:basic"));
    public static void tick(boolean initalised, Entity me) {
        Vec3 pos = me.position();
        Level level = me.level();
        if (!initalised) {
            var effect = new EntityEffect(fx_basic,level,me);
            effect.setForcedDeath(true);
            effect.setAllowMulti(true);
            effect.start();
        }
    }
}