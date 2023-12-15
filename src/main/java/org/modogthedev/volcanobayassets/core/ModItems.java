package org.modogthedev.volcanobayassets.core;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.core.items.Scroll;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VolcanobayAssets.MODID);
    public static final RegistryObject<Scroll> SCROLL_HEAL = ITEMS.register("scroll_heal", () -> new Scroll(new Scroll.Properties().spellType(1),new Item.Properties().stacksTo(1).durability(10)));
    public static final RegistryObject<Scroll> SCROLL_BASIC = ITEMS.register("scroll_basic", () -> new Scroll(new Scroll.Properties().spellType(2),new Item.Properties().stacksTo(1).durability(10)));
    public static final RegistryObject<Scroll> SCROLL_MISSILE = ITEMS.register("scroll_missile", () -> new Scroll(new Scroll.Properties().spellType(3),new Item.Properties().stacksTo(1).durability(10)));
    public static final RegistryObject<Scroll> SCROLL_STRENGTHEN = ITEMS.register("scroll_strengthen", () -> new Scroll(new Scroll.Properties().spellType(4),new Item.Properties().stacksTo(1).durability(10)));
    public static final RegistryObject<Scroll> SCROLL_FIREWORK = ITEMS.register("scroll_firework", () -> new Scroll(new Scroll.Properties().spellType(5),new Item.Properties().stacksTo(1).durability(10)));
    public static final RegistryObject<Scroll> SCROLL_BOMB = ITEMS.register("scroll_bomb", () -> new Scroll(new Scroll.Properties().spellType(7),new Item.Properties().stacksTo(1).durability(10)));
    public static final RegistryObject<Scroll> SCROLL_PILLAR_ICE = ITEMS.register("scroll_pillar_ice", () -> new Scroll(new Scroll.Properties().spellType(9),new Item.Properties().stacksTo(1).durability(10)));
    public static final RegistryObject<ForgeSpawnEggItem> GUARD_SPAWN_EGG = ITEMS.register("guard_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.GUARD, 0x1cadc9,0xba8106, new Item.Properties()));
}
