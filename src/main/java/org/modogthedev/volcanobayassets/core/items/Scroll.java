package org.modogthedev.volcanobayassets.core.items;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.modogthedev.volcanobayassets.core.ModAttributes;
import org.modogthedev.volcanobayassets.core.ModEntities;
import org.modogthedev.volcanobayassets.core.entities.GuardEntity;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;
import org.modogthedev.volcanobayassets.core.event.PlayerTickHandler;

public class Scroll extends Item {
    private static final EntityDataAccessor<String> DATA_SPELL_TYPE = SynchedEntityData.defineId(SpellEntity.class, EntityDataSerializers.STRING);
    public int type = 0;

    public Scroll(Properties properties, Item.Properties properties1) {
        super(properties1);
        this.type = properties.type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        int amount = 1;
        if (type == 3) {
            amount = 10;
        }
        if (!level.isClientSide()) {
            GuardEntity.playerWarnOthers(player,player,15);
        }
        for (int i = 0; i < amount; i++) {
            SpellEntity spellEntity = ModEntities.SPELL_ENTITY.get().create(level);
            spellEntity.setPos(player.position());
            spellEntity.setXRot(player.getXRot()+getRand());
            spellEntity.setYRot(player.getYHeadRot()+getRand());
            spellEntity.setSpellType(type);
            spellEntity.setOwner(player);
            spellEntity.power = (int) player.getAttribute(ModAttributes.magicDamage.get()).getValue();
            spellEntity.checkDataDiffers();
            level.addFreshEntity(spellEntity);
        }
        player.getCooldowns().addCooldown(player.getItemInHand(hand).getItem(),SpellEntity.returnCooldown(type));
        return super.use(level, player, hand);
    }

    public static class Properties {
        public int type = 0;
        public Scroll.Properties spellType(int amount) {
            this.type = amount;
            return this;
        }
    }
    public static float getRand() {
        return (float) (Math.random()*8-4);
    }
}
