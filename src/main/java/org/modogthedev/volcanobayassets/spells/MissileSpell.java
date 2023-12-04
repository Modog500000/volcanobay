package org.modogthedev.volcanobayassets.spells;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class MissileSpell {
    static final FX fx_missile = FXHelper.getFX(new ResourceLocation("volcanobayassets:missile"));
    static final FX fx_block = FXHelper.getFX(new ResourceLocation("volcanobayassets:block"));
    public static boolean tick(boolean initalised, SpellEntity me,Entity owner) {
        Vec3 pos = me.position();
        Level level = me.level();
        if (!initalised) {
            var effect = new EntityEffect(fx_missile,level,me);
            effect.setForcedDeath(false);
            effect.setAllowMulti(true);
            effect.start();
        }
        if (me.markForRemoval < 0) {
            for (SpellEntity entity : SpellEntity.getListofSpellsInRange(2, pos, me)) {
                if (entity != me) {
                    var block = new EntityEffect(fx_block, level, entity);
                    block.setForcedDeath(false);
                    block.setAllowMulti(true);
                    block.start();
                    entity.markForRemoval = 5;
                    var block2 = new EntityEffect(fx_block, level, me);
                    block2.setForcedDeath(false);
                    block2.setAllowMulti(true);
                    block2.start();
                    me.markForRemoval = 5;
                }
            }
            for (LivingEntity entity : SpellEntity.getListofEntitesInRange(2, pos, me)) {
                if (entity != owner) {
                    entity.hurt(me.damageSources().magic(), 5);
                    var effect = new EntityEffect(fx_block, level, entity);
                    effect.setForcedDeath(false);
                    effect.setAllowMulti(true);
                    effect.start();
                    return true;
                }
            }
        }
        return false;
    }
}
