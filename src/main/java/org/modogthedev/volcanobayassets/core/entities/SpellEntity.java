package org.modogthedev.volcanobayassets.core.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
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
import org.modogthedev.volcanobayassets.spells.MissileSpell;
import org.modogthedev.volcanobayassets.spells.StrengthenSpell;

import java.util.ArrayList;
import java.util.List;

public class SpellEntity extends Entity {
    public int lifetime = 0;
    public int restorableLifetime = 0;
    public Integer type = 0;
    public Entity owner = this;
    public int power = 1;
    public boolean projectile = false;
    public int markForRemoval = -1;

    public static final EntityDataAccessor<Integer> DATA_SPELL_TYPE = SynchedEntityData.defineId(SpellEntity.class, EntityDataSerializers.INT);
    private BlockPos source = this.blockPosition();
    private boolean initialise = false;

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
                HealSpell.tick(initialise, this, restorableLifetime);
                if (restorableLifetime > 400) {
                    this.kill();
                }
                initialise = true;
            }
            case 2 -> {
                BasicSpell.tick(initialise, this);
                if (lifetime > 60) {
                    this.kill();
                }
                initialise = true;
            }
            case 3 -> {
                if (MissileSpell.tick(initialise, this,owner)) {
                    this.setDeltaMovement(this.getDeltaMovement().scale(0.8));
                }
                projectile = true;
                if (restorableLifetime > 60) {
                    this.kill();
                }
            }
            case 4 -> {
                StrengthenSpell.tick(initialise,this);
                projectile = true;
                if (lifetime > 60) {
                    this.kill();
                }
            }
        }
        if (projectile) {
            if (!initialise) {
                shootFromRotation(this.getXRot(),this.getYRot(),0.0F, (float) (1.5+Math.random()-.5), 1.0F);
                initialise = true;

            }
            this.setDeltaMovement(this.getDeltaMovement().x,this.getDeltaMovement().y-0.01,this.getDeltaMovement().z);
            if (this.getDeltaMovement().equals(Vec3.ZERO)) {
                this.kill();
            }
        }
        if (markForRemoval > 0) {
            markForRemoval--;
        } else if (markForRemoval == 0) {
            this.kill();
        }
        lifetime++;
        restorableLifetime++;

        this.setDeltaMovement(this.getDeltaMovement().scale(0.999));
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
        if (!me.level().isClientSide) {
            List<LivingEntity> entitiesAffected = me.level().getEntitiesOfClass(LivingEntity.class, me.getBoundingBox().inflate(dist));
            for (LivingEntity entity : entitiesAffected) {
                if (entity.position().distanceTo(source) <= dist) {
                    entitiesFinal.add(entity);
                }
            }
        }
        return entitiesFinal;
    }
    public static List<Entity> getListofAllEntitesInRange(int dist, Vec3 source, Entity me) {
        List<Entity> entitiesFinal = new ArrayList<>();
        if (!me.level().isClientSide) {
            List<Entity> entitiesAffected = me.level().getEntitiesOfClass(Entity.class, me.getBoundingBox().inflate(dist));
            for (Entity entity : entitiesAffected) {
                if (entity.position().distanceTo(source) <= dist) {
                    entitiesFinal.add(entity);
                }
            }
        }
        return entitiesFinal;
    }
    public static List<SpellEntity> getListofSpellsInRange(int dist, Vec3 source, Entity me) {
        List<SpellEntity> entitiesFinal = new ArrayList<>();
        if (!me.level().isClientSide) {
            List<SpellEntity> entitiesAffected = me.level().getEntitiesOfClass(SpellEntity.class, me.getBoundingBox().inflate(dist));
            for (SpellEntity entity : entitiesAffected) {
                if (entity.position().distanceTo(source) <= dist) {
                    entitiesFinal.add(entity);
                }
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
    public void setOwner(Entity newOwner) {
        owner = newOwner;
    }
    public void moveDir() {

    }
    public void shoot(double p_37266_, double p_37267_, double p_37268_, float p_37269_, float p_37270_) {
        Vec3 vec3 = (new Vec3(p_37266_, p_37267_, p_37268_)).normalize().add(this.random.triangle(0.0D, 0.0172275D * (double)p_37270_), this.random.triangle(0.0D, 0.0172275D * (double)p_37270_), this.random.triangle(0.0D, 0.0172275D * (double)p_37270_)).scale((double)p_37269_);
        this.setDeltaMovement(vec3);
        double d0 = vec3.horizontalDistance();
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
        this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    public void shootFromRotation(float p_37253_, float p_37254_, float p_37255_, float p_37256_, float p_37257_) {
        float f = -Mth.sin(p_37254_ * ((float)Math.PI / 180F)) * Mth.cos(p_37253_ * ((float)Math.PI / 180F));
        float f1 = -Mth.sin((p_37253_ + p_37255_) * ((float)Math.PI / 180F));
        float f2 = Mth.cos(p_37254_ * ((float)Math.PI / 180F)) * Mth.cos(p_37253_ * ((float)Math.PI / 180F));
        this.shoot((double)f, (double)f1, (double)f2, p_37256_, p_37257_);
    }
}
