package org.modogthedev.volcanobay.core;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.modogthedev.volcanobay.Volcanobay;

public class ModItemGroup {
    public static final ItemGroup TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Volcanobay.MOD_ID, "volcanobay"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.volcanobay"))
                    .icon(() -> new ItemStack(ModItems.KEY)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.SANDSTONE_BRICKS_VARIANT);
                    }).build());
    public static void registerItemGroups() {
    }
}
