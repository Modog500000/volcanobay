package org.modogthedev.volcanobayassets.core;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.Item;
import net.minecraft.core.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.RegistryObject;
import org.modogthedev.volcanobayassets.VolcanobayAssets;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, VolcanobayAssets.MODID);

    public static RegistryObject<Attribute> magicDamage = ATTRIBUTES.register("magic_damage", () -> new RangedAttribute("volcanobayassets.attribute.magic_damage.name", 0, 0, 2048));
    public static RegistryObject<Attribute> stealth = ATTRIBUTES.register("stealth", () -> new RangedAttribute("volcanobayassets.attribute.stealth.name", 0, 0, 2048));
}
