package org.modogthedev.volcanobayassets.core;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.modogthedev.volcanobayassets.VolcanobayAssets;
import org.modogthedev.volcanobayassets.core.items.Scroll;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VolcanobayAssets.MODID);
    public static final RegistryObject<Scroll> SCROLL_HEAL = ITEMS.register("scroll_heal", () -> new Scroll(new Scroll.Properties().spellType(1),new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Scroll> SCROLL_BASIC = ITEMS.register("scroll_basic", () -> new Scroll(new Scroll.Properties().spellType(2),new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Scroll> SCROLL_MISSILE = ITEMS.register("scroll_missile", () -> new Scroll(new Scroll.Properties().spellType(3),new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Scroll> SCROLL_STRENGTHEN = ITEMS.register("scroll_strengthen", () -> new Scroll(new Scroll.Properties().spellType(4),new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Scroll> SCROLL_FIRWORK = ITEMS.register("scroll_firework", () -> new Scroll(new Scroll.Properties().spellType(5),new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Scroll> SCROLL_BOMB = ITEMS.register("scroll_bomb", () -> new Scroll(new Scroll.Properties().spellType(7),new Item.Properties().stacksTo(1)));
}
