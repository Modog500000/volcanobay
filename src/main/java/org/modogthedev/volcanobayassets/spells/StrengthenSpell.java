package org.modogthedev.volcanobayassets.spells;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class StrengthenSpell {
    public static void tick(boolean initalised, SpellEntity me) {
        final boolean client = me.level().isClientSide;
        Vec3 pos = me.position();
        Level level = me.level();
        if (!initalised && client) {
            final FX fx_strengthen = FXHelper.getFX(new ResourceLocation("volcanobayassets:strengthen"));
            var effect = new EntityEffect(fx_strengthen, level, me);
            effect.setForcedDeath(false);
            effect.setAllowMulti(true);
            effect.start();
        }
        if (me.markForRemoval < 0) {
            for (SpellEntity entity : SpellEntity.getListofSpellsInRange(5, pos, me)) {
                if (entity != me) {
                    if (entity.type != 4) {
                        me.setPos(entity.position());
                        entity.restorableLifetime = 0;
                        entity.power++;
                        SpellEntity.newBasic(entity.position(),me.level(),entity,0);
                        me.markForRemoval = 2;
                    }
                }
            }
        }
    }
}
