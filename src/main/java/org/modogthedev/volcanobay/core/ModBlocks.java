package org.modogthedev.volcanobay.core;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.modogthedev.volcanobay.Volcanobay;

public class ModBlocks {
    public static final Block SANDSTONE_BRICKS_VARIANT = registerBlock("sandstone_bricks_variant",
            new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Volcanobay.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(Volcanobay.MOD_ID),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks() {
    }
}
