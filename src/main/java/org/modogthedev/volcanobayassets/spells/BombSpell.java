package org.modogthedev.volcanobayassets.spells;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class BombSpell {


    public static void tick(boolean initalised, SpellEntity me,Entity owner) {
        final boolean client = me.level().isClientSide;
        Vec3 pos = me.position();
        Level level = me.level();
        if (!initalised && client) {
            final FX fx_bomb = FXHelper.getFX(new ResourceLocation("volcanobayassets:bomb"));
            var effect = new EntityEffect(fx_bomb, level, me);
            effect.setForcedDeath(false);
            effect.setAllowMulti(true);
            effect.start();
        }
        if (me.markForRemoval == -1) {
            if (level.getBlockState(new BlockPos((int) me.getX(), (int) (me.getY()), (int) me.getZ())).getBlock() != Blocks.AIR) {
                SpellEntity.newBasic(me.position(), me.level(), me, 2);
                for (LivingEntity entity : SpellEntity.getListofEntitesInRange(5, pos, me)) {
                    if (entity != owner) {
                        entity.hurt(owner.damageSources().magic(), 5+me.power);
                        SpellEntity.newBasic(entity.position(), me.level(), entity, 1);
                    }
                }
                me.markForRemoval = 0;
            }
            for (SpellEntity entity : SpellEntity.getListofSpellsInRange(2, pos, me)) {
                if (entity != me) {
                    if (entity.type == 3) {
                        SpellEntity.newBasic(me.position(), me.level(), me, 2);
                        for (LivingEntity entity2 : SpellEntity.getListofEntitesInRange(5, pos, me)) {
                            entity2.hurt(owner.damageSources().magic(), 5+(me.power*2));
                            SpellEntity.newBasic(entity2.position(), me.level(), entity, 1);
                        }
                        me.markForRemoval = 0;
                    }
                }
            }
        }
    }
}
