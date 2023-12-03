package org.modogthedev.volcanobayassets.core.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.spells.BasicSpell;
import org.modogthedev.volcanobayassets.spells.HealSpell;

import java.util.ArrayList;
import java.util.List;

public class SpellEntity extends Entity {
    public int lifetime = 0;
    public Integer type = 0;
    public int power = 1;

    public static final EntityDataAccessor<Integer> DATA_SPELL_TYPE = SynchedEntityData.defineId(SpellEntity.class, EntityDataSerializers.INT);
    private BlockPos source = this.blockPosition();
    private boolean initalised = false;

    public SpellEntity(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_SPELL_TYPE, 1);
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {
        ListTag list = tag.getList("spell", Tag.TAG_COMPOUND);
        for (Tag t : list) {
            CompoundTag spellTag = (CompoundTag) t;
            this.entityData.set(DATA_SPELL_TYPE,spellTag.getInt("spell"));
            power = spellTag.getInt("power");
        }
    }
    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
        ListTag list = new ListTag();
        CompoundTag spellTag = new CompoundTag();
        spellTag.putInt("power", power);
        spellTag.putInt("spell",(this.entityData.get(DATA_SPELL_TYPE)));
        list.add(spellTag);
        tag.put("spell", list);
    }
    @Override
    public boolean save(CompoundTag tag) {
        ListTag list = new ListTag();
        CompoundTag spellTag = new CompoundTag();
        spellTag.putInt("power", power);
        spellTag.putInt("spell",(this.entityData.get(DATA_SPELL_TYPE)));
        list.add(spellTag);
        tag.put("spell", list);
        return super.save(tag);
    }

    @Override
    public void tick() {
        super.tick();
        int switchType = this.entityData.get(DATA_SPELL_TYPE);
        switch (switchType) {
            case 0 -> {
            }
            case 1 -> {
                HealSpell.tick(initalised, this);
                if (lifetime > 400) {
                    this.kill();
                }
                initalised = true;
            }
            case 2 -> {
                BasicSpell.tick(initalised, this);
                if (lifetime > 60) {
                    this.kill();
                }
                initalised = true;
            }
        }
        lifetime++;
        this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
        this.move(MoverType.SELF,this.getDeltaMovement());
    }

    @Override
    public boolean isPushable() {
        return true;
    }
    @Override
    public void push(@NotNull Entity p_20293_) {
        super.push(this);
    }
    public static List<LivingEntity> getListofEntitesInRange(int dist, Vec3 source, Entity me) {
        List<LivingEntity> entitiesFinal = new ArrayList<>();
        List<LivingEntity> entitiesAffected = me.level().getEntitiesOfClass(LivingEntity.class, me.getBoundingBox().inflate(dist));
        for (LivingEntity entity : entitiesAffected) {
            if (entity.position().distanceTo(source) <= dist) {
                entitiesFinal.add(entity);
            }
        }
        return entitiesFinal;
    }
    public void checkDataDiffers() {
        if (this.getEntityData().get(DATA_SPELL_TYPE) != type) {
            this.getEntityData().set(DATA_SPELL_TYPE, type);
        }
    }
    public void setSpellType(Integer newType) {
        type = newType;
    }
}
