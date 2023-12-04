package org.modogthedev.volcanobayassets.spells;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class StrengthenSpell {
    static final FX fx_missile = FXHelper.getFX(new ResourceLocation("volcanobayassets:missile"));
    static final FX fx_basic = FXHelper.getFX(new ResourceLocation("volcanobayassets:basic"));
    public static void tick(boolean initalised, Entity me) {
        Vec3 pos = me.position();
        Level level = me.level();
        if (!initalised) {
            var effect = new EntityEffect(fx_missile,level,me);
            effect.setForcedDeath(false);
            effect.setAllowMulti(true);
            effect.start();
        }
        for (SpellEntity entity: SpellEntity.getListofSpellsInRange(20,pos,me)) {
            if (entity != me) {
                if (entity.type != 4) {
                    me.setPos(entity.position());
                    entity.restorableLifetime = 0;
                    entity.power++;
                    var effect2 = new EntityEffect(fx_basic, level, entity);
                    effect2.setForcedDeath(false);
                    effect2.setAllowMulti(true);
                    effect2.start();
                    me.kill();
                }
            }
        }
    }
}
