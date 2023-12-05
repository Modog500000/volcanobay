package org.modogthedev.volcanobayassets.spells;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BasicSpell {
    public static void tick(boolean initalised, Entity me, Entity owner, int special) {
        final boolean client = me.level().isClientSide;
        Vec3 pos = me.position();
        Level level = me.level();
        if (!initalised && client) {
            final FX fx_basic = FXHelper.getFX(new ResourceLocation("volcanobayassets:basic"));
            final FX fx_block = FXHelper.getFX(new ResourceLocation("volcanobayassets:block"));
            final FX fx_explosion = FXHelper.getFX(new ResourceLocation("volcanobayassets:explosion"));
            if (special == 0) {
                var effect = new EntityEffect(fx_block,level,owner);
                effect.setForcedDeath(false);
                effect.setAllowMulti(true);
                effect.start();
            } else if (special == 1) {
                var effect = new EntityEffect(fx_basic, level, owner);
                effect.setForcedDeath(false);
                effect.setAllowMulti(true);
                effect.start();
            } else if (special == 2) {
                var effect = new EntityEffect(fx_explosion, level, owner);
                effect.setForcedDeath(false);
                effect.setAllowMulti(true);
                effect.start();
            }
        }
    }
}