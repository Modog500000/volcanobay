package org.modogthedev.volcanobayassets.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.core.entities.SpellEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VolcanobayAssets.MODID);

    public static final RegistryObject<EntityType<SpellEntity>> SPELL_ENTITY = ENTITIES.register("spell_entity",
            () -> EntityType.Builder.of(SpellEntity::new, MobCategory.MISC).sized(0.5f,0.5f).build(new ResourceLocation(VolcanobayAssets.MODID, "spell_entity").toString()));
}
