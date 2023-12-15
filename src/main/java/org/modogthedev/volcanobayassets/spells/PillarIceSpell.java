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
import org.modogthedev.volcanobayassets.core.ModAttributes;
import org.modogthedev.volcanobayassets.core.ModEntities;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class PillarIceSpell {
    public static void tick(boolean initalised, SpellEntity me, boolean hitEntity, Entity owner) {
        final boolean client = me.level().isClientSide;
        Vec3 pos = me.position();
        Level level = me.level();
        if (!hitEntity) {
            if (!initalised && client) {
                final FX fx_frost_trailn = FXHelper.getFX(new ResourceLocation("volcanobayassets:frost_trail"));
                var effect = new EntityEffect(fx_frost_trailn, level, me);
                effect.setForcedDeath(false);
                effect.setAllowMulti(true);
                effect.start();
            }
            if (me.markForRemoval == -1) {
                if (level.getBlockState(new BlockPos((int) me.getX(), (int) (me.getY()), (int) me.getZ())).getBlock() != Blocks.AIR) {
                    SpellEntity spellEntity = ModEntities.SPELL_ENTITY.get().create(level);
                    spellEntity.setPos(me.blockPosition().getCenter());
                    spellEntity.setSpellType(10);
                    spellEntity.setOwner(owner);
                    spellEntity.checkDataDiffers();
                    level.addFreshEntity(spellEntity);
                    me.markForRemoval = 0;
                }
            }
        }
        if (hitEntity) {
            if (client && !initalised) {
                final FX fx_pillar = FXHelper.getFX(new ResourceLocation("volcanobayassets:frost_pillar"));
                var effect = new EntityEffect(fx_pillar, level, me);
                effect.setForcedDeath(false);
                effect.setAllowMulti(true);
                effect.start();
            }
            if (me.lifetime/5 == Math.floor(me.lifetime/5) && me.lifetime<20) {
                if (level.getBlockState(new BlockPos((int) me.getX(), (int) (me.getY()+Math.floor(me.lifetime/5)), (int) me.getZ()-1)).getBlock() == Blocks.AIR) {
                    level.setBlock(new BlockPos((int) me.getX(), (int) (me.getY()+Math.floor(me.lifetime/5)), (int) me.getZ()-1), Blocks.PACKED_ICE.defaultBlockState(), 1);

                }
            }
            if (me.lifetime>=120) {
                for (int i = (int) me.getY(); i < me.getY()+6; i++) {
                    if (level.getBlockState(new BlockPos((int) me.getX(), i, (int) me.getZ()-1)).getBlock() == Blocks.PACKED_ICE) {
                        level.setBlock(new BlockPos((int) me.getX(), i, (int) me.getZ() - 1), Blocks.AIR.defaultBlockState(), 1);
                    }
                }
                me.markForRemoval = 0;
            }
        }
    }
}
