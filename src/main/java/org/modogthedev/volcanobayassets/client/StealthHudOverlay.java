package org.modogthedev.volcanobayassets.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.modogthedev.volcanobayassets.VolcanobayAssets;

public class StealthHudOverlay{
    public static int increase = 0;
    public static int decrease = 0;
    private static int dir = 1;
    private static int tick = 0;
    private static final ResourceLocation SEEN = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/seen.png");
    private static final ResourceLocation COMPASS = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/compass.png");
    private static final ResourceLocation UNSEEN = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/unseen.png");
    private static final ResourceLocation INCREASE = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/increase.png");
    private static final ResourceLocation DECREASE = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/decrease.png");
    private static final ResourceLocation HEAL = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/particle/heal.png");
    private static final ResourceLocation TRAIL = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/particle/trail.png");
    private static final ResourceLocation EMPTY = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/particle/empty.png");
    public static final IGuiOverlay HUD_STEALTH = ((ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) -> {
        tick++;
        if (tick > 20) {
            tick = 0;
            if (dir == 1) {
                dir = -1;
            } else {
                dir = 1;
            }
        }
        int width = guiGraphics.guiWidth();
        int height = guiGraphics.guiHeight();
        int x = width;
        int y = height;
        if (ClientStealthData.getPlayerStealth() > 50) {
            guiGraphics.blit(UNSEEN,x-34,y-34,0,0,32,32,
                    32,32);
        } else {
            guiGraphics.blit(SEEN, x - 34, y - 34, 0, 0, 32, 32,
                    32, 32);
        }
        if (increase > 0) {
            increase--;
            guiGraphics.blit(INCREASE,x-36,y-34+dir*2,0,0,16,16,16,16);
        }
        if (decrease > 0) {
            decrease--;
            guiGraphics.blit(DECREASE,x-36,y-34+dir*2,0,0,16,16,16,16);
        }
        guiGraphics.drawCenteredString(gui.getFont(),String.valueOf(ClientStealthData.getPlayerStealth()),x-16,y-40,0);
        Player player = Minecraft.getInstance().player;
        Math.floor(player.getVisualRotationYInDegrees()/270);
        guiGraphics.blit(COMPASS,x-(width/2)-180,11,0,0,360,20,
                32,32);
        guiGraphics.drawCenteredString(gui.getFont(), "|", (int) (x-(width/2)),32,0xffffff);
        guiGraphics.drawCenteredString(gui.getFont(),"S", (int) (x-(width/2)+player.getVisualRotationYInDegrees()-180+Math.floor((player.getVisualRotationYInDegrees()-180)/360)*-360)-180,16,0xffffff);
        guiGraphics.drawCenteredString(gui.getFont(),"W", (int) (x-(width/2)+player.getVisualRotationYInDegrees()-270+Math.floor((player.getVisualRotationYInDegrees()-270)/360)*-360)-180,16,0xffffff);
        guiGraphics.drawCenteredString(gui.getFont(),"E", (int) (x-(width/2)+player.getVisualRotationYInDegrees()-90+Math.floor((player.getVisualRotationYInDegrees()-90)/360)*-360)-180,16,0xffffff);
        guiGraphics.drawCenteredString(gui.getFont(),"N", (int) (x-(width/2)+player.getVisualRotationYInDegrees()+Math.floor((player.getVisualRotationYInDegrees())/360)*-360)-180,16,0xffffff);
    });
}
