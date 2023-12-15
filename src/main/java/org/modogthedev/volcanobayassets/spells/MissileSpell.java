package org.modogthedev.volcanobayassets.spells;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class MissileSpell {


    public static boolean tick(boolean initalised, SpellEntity me,Entity owner) {
        final boolean client = me.level().isClientSide;
        Vec3 pos = me.position();
        Level level = me.level();
        if (!initalised && client) {
            final FX fx_missile = FXHelper.getFX(new ResourceLocation("volcanobayassets:missile"));
            var effect = new EntityEffect(fx_missile,level,me);
            effect.setForcedDeath(false);
            effect.setAllowMulti(true);
            effect.start();
        }
        if (me.markForRemoval < 0) {
            for (SpellEntity entity : SpellEntity.getListofSpellsInRange(2, pos, me)) {
                if (entity != me) {
                    if (entity.type == 3) {
                        SpellEntity.newBasic(entity.position(),me.level(),entity,1);
                        entity.markForRemoval = 5;
                        SpellEntity.newBasic(entity.position(),me.level(),entity,1);
                        me.markForRemoval = 5;
                    }
                }
            }
            for (LivingEntity entity : SpellEntity.getListofEntitesInRange(2, pos, me)) {
                if (entity != owner) {
                    entity.hurt(owner.damageSources().magic(), 3+(me.power*4));

                    SpellEntity.newBasic(entity.position(),me.level(),entity,1);
                    me.markForRemoval = 1;
                    return true;
                }
            }
        }
        return false;
    }
}
