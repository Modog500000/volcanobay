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

public class FireworkSpell {
    public static void tick(boolean initalised, Entity me,Integer lifetime, Entity owner) {
        final boolean client = me.level().isClientSide;
        Vec3 pos = me.position();
        Level level = me.level();
        if (lifetime == 100) {
            for (LivingEntity entity : SpellEntity.getListofEntitesInRange(20, pos, me)) {
                if (entity != owner) {
                    entity.hurt(me.damageSources().magic(), 15);
                    if (client) {
                        final FX fx_block = FXHelper.getFX(new ResourceLocation("volcanobayassets:block"));
                        var effect = new EntityEffect(fx_block, level, entity);
                        effect.setForcedDeath(false);
                        effect.setAllowMulti(true);
                        effect.start();
                    }
                }
            }
        }
        if (!initalised && client) {
            final FX fx_firework = FXHelper.getFX(new ResourceLocation("volcanobayassets:firework"));
            var effect = new EntityEffect(fx_firework,level,me);
            effect.setForcedDeath(false);
            effect.setAllowMulti(true);
            effect.start();
        }
    }
}
