package org.modogthedev.volcanobayassets.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.modogthedev.volcanobayassets.VolcanobayAssets;

public class StealthHudOverlay{
    public static int increase = 0;
    public static int decrease = 0;
    public static int offset = 0;
    private static int dir = 1;
    private static int tick = 0;
    private static final ResourceLocation SEEN = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/seen.png");
    private static final ResourceLocation UNSEEN = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/unseen.png");
    private static final ResourceLocation INCREASE = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/increase.png");
    private static final ResourceLocation DECREASE = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/decrease.png");
    public static final IGuiOverlay HUD_STEALTH = ((ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) -> {
        int width = guiGraphics.guiWidth();
        int height = guiGraphics.guiHeight();
        int x = width;
        int y = height;
        guiGraphics.blit(SEEN,x-34,y-34,0,0,32,32,
                32,32);
        guiGraphics.drawCenteredString(gui.getFont(),String.valueOf(ClientStealthData.getPlayerStealth()),x-16,y-40,0);
    });
}
