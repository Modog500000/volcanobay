package org.modogthedev.volcanobayassets.core;


import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.modogthedev.volcanobayassets.VolcanobayAssets;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VolcanobayAssets.MODID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SCROLL_HEAL.get()))
                    .title(Component.translatable("itemgroup.volcanobay"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SCROLL_HEAL.get().getDefaultInstance());
                        pOutput.accept(ModItems.SCROLL_BASIC.get().getDefaultInstance());
                        pOutput.accept(ModItems.SCROLL_MISSILE.get().getDefaultInstance());
                        pOutput.accept(ModItems.SCROLL_STRENGTHEN.get().getDefaultInstance());
                        pOutput.accept(ModItems.SCROLL_FIRWORK.get().getDefaultInstance());
                        pOutput.accept(ModItems.SCROLL_BOMB.get().getDefaultInstance());
                    })
                    .build());
}
