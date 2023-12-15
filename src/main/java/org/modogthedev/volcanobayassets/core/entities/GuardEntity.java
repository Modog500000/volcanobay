package org.modogthedev.volcanobayassets.core.entities;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.modogthedev.volcanobayassets.VolcanobayAssets;

public class GuardEntity extends Monster {

    public GuardEntity(EntityType<? extends Monster> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this,1));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6));
        super.registerGoals();
    }
    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes().add(Attributes.MOVEMENT_SPEED, 0.3).add(Attributes.MAX_HEALTH, 15).add(Attributes.FOLLOW_RANGE, 16).add(Attributes.ATTACK_DAMAGE, 1).add(Attributes.ATTACK_SPEED, 1).add(ForgeMod.ENTITY_REACH.get(), 4).add(Attributes.ATTACK_KNOCKBACK, 1);
    }

    @Override
    public boolean hurt(DamageSource source, float p_21017_) {
        this.setTarget((LivingEntity) source.getEntity());
        return super.hurt(source, p_21017_);
    }
}