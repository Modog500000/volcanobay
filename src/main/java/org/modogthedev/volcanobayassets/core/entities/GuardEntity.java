package org.modogthedev.volcanobayassets.core.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.core.ModAttributes;
import org.modogthedev.volcanobayassets.core.event.PlayerTickHandler;
import org.modogthedev.volcanobayassets.core.networking.ModMessages;
import org.modogthedev.volcanobayassets.core.networking.packet.StealthSyncDataS2CPacket;
import org.modogthedev.volcanobayassets.core.util.PlayerStealthProvider;
import org.modogthedev.volcanobayassets.core.util.StealthData;

import java.util.List;
import java.util.Objects;

public class GuardEntity extends Monster {

    public GuardEntity(EntityType<? extends Monster> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }
    public int skin = (int) Math.floor(Math.random()*5);

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this,1));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        super.registerGoals();
    }
    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes().add(Attributes.MOVEMENT_SPEED, 0.3).add(Attributes.MAX_HEALTH, 15).add(Attributes.FOLLOW_RANGE, 16).add(Attributes.ATTACK_DAMAGE, 1).add(Attributes.ATTACK_SPEED, 1).add(ForgeMod.ENTITY_REACH.get(), 4).add(Attributes.ATTACK_KNOCKBACK, 1);
    }

    @Override
    public boolean hurt(DamageSource source, float p_21017_) {
        this.setTarget((LivingEntity) source.getEntity());
        warnOthers((LivingEntity) source.getEntity(),10);
        return super.hurt(source, p_21017_);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
        this.setCanPickUpLoot(true);
        this.setItemSlot(EquipmentSlot.HEAD, Items.IRON_HELMET.getDefaultInstance());
        this.setItemSlot(EquipmentSlot.CHEST, Items.IRON_CHESTPLATE.getDefaultInstance());
        this.setItemSlot(EquipmentSlot.LEGS, Items.IRON_LEGGINGS.getDefaultInstance());
        this.setItemSlot(EquipmentSlot.FEET, Items.IRON_BOOTS.getDefaultInstance());
        this.setItemSlot(EquipmentSlot.MAINHAND, Items.IRON_SWORD.getDefaultInstance());

        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide) {
                List<ServerPlayer> getPlayers = this.level().getEntitiesOfClass(ServerPlayer.class, this.getBoundingBox().inflate(10));
            for (ServerPlayer player : getPlayers) {
                int stealth = (int) player.getAttribute(ModAttributes.stealth.get()).getValue();
                Vec3 vec3 = this.getViewVector(1.0F).normalize();
                Vec3 vec31 = new Vec3(player.getX() - this.getX(), player.getEyeY() - this.getEyeY(), player.getZ() - this.getZ());
                double d0 = vec31.length();
                vec31 = vec31.normalize();
                double d1 = vec3.dot(vec31);
                boolean seen =d1 > Math.max(0.985,0.7) && this.hasLineOfSight(player);
                if (seen && player.gameMode.isSurvival()) {
                    if (player.getMainHandItem().getItem() instanceof SwordItem) {
                        newTarget(player, this);
                        warnOthers(player,5);
                    }
                    player.getCapability(PlayerStealthProvider.PLAYER_STEALTH).ifPresent(stealthData -> {
                       if (stealthData.getStealth() < 0) {
                           newTarget(player, this);
                       } else if (stealthData.getStealth() > 0) {
                           PlayerTickHandler.setStealth(player, 0);
                       }
                    });
                    if (Objects.equals(this.getTarget(), player)) {
                        PlayerTickHandler.setStealth(player, -5);
                    }
                }
            }
        }
        super.tick();
        List<Monster> extraTargets = this.level().getEntitiesOfClass(Monster.class, this.getBoundingBox().inflate(10));
        for (Monster monster : extraTargets) {
            if (!(monster instanceof GuardEntity)) {
                if (this.hasLineOfSight(monster)) {
                    this.newTarget(monster, this);
                }
            }
        }
    }
    public void newTarget(LivingEntity target, GuardEntity guard) {
        if (guard.getTarget() == null) {
            guard.setTarget(target);
            if (target instanceof ServerPlayer stealthTarget) {
                PlayerTickHandler.setStealth(stealthTarget, -400);
            }
        } else if (!guard.hasLineOfSight(guard.getTarget())) {
            guard.setTarget(target);
            if (target instanceof ServerPlayer stealthTarget) {
                PlayerTickHandler.setStealth(stealthTarget, -400);
            }
        }
    }
    public void warnOthers(LivingEntity target, int radius) {
        List<GuardEntity> getGuards = this.level().getEntitiesOfClass(GuardEntity.class, this.getBoundingBox().inflate(radius));
        for (GuardEntity guard : getGuards) {
            if (guard.hasLineOfSight(this)) {
                newTarget(target, guard);
            }
        }
    }

    @Override
    protected float getEquipmentDropChance(EquipmentSlot p_21520_) {
        return 0;
    }
}