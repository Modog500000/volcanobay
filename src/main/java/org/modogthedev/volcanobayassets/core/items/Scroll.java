package org.modogthedev.volcanobayassets.core.items;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import org.modogthedev.volcanobayassets.core.ModEntities;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class Scroll extends Item {
    private static final EntityDataAccessor<String> DATA_SPELL_TYPE = SynchedEntityData.defineId(SpellEntity.class, EntityDataSerializers.STRING);
    public int type = 0;

    public Scroll(Properties properties, Item.Properties properties1) {
        super(properties1);
        this.type = properties.type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {
        if (Dist.CLIENT.isDedicatedServer()) {

        }
        SpellEntity spellEntity = ModEntities.SPELL_ENTITY.get().create(level);
        spellEntity.setPos(player.position());
        spellEntity.setSpellType(type);
        spellEntity.checkDataDiffers();
        level.addFreshEntity(spellEntity);
        return super.use(level, player, p_41434_);
    }

    public static class Properties {
        public int type = 0;
        public Scroll.Properties spellType(int amount) {
            this.type = amount;
            return this;
        }
    }
}
