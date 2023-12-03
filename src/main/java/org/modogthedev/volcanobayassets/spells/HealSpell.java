package org.modogthedev.volcanobayassets.spells;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class HealSpell {
    static final FX fx_heal = FXHelper.getFX(new ResourceLocation("volcanobayassets:heal"));
    public static void tick(boolean initalised, Entity me) {
        Vec3 pos = me.position();
        Level level = me.level();
        if (!initalised) {
            var effect = new EntityEffect(fx_heal,level,me);
            effect.setForcedDeath(true);
            effect.setAllowMulti(true);
            effect.start();
        }
        for (LivingEntity entity: SpellEntity.getListofEntitesInRange(5,pos,me)) {
            if (entity instanceof Player) {
                entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 0), me);
            }
        }
    }
}
