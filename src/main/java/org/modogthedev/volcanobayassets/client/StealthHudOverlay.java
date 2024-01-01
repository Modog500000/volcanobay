package org.modogthedev.volcanobayassets.client;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.modogthedev.volcanobayassets.VolcanobayAssets;

import java.awt.*;
import java.math.BigInteger;

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
    private static final ResourceLocation THREAT_1 = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/threat_1.png");
    private static final ResourceLocation DECREASE = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/stealth/decrease.png");
    private static final ResourceLocation HEAL = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/particle/heal.png");
    private static final ResourceLocation CROSS = new ResourceLocation(VolcanobayAssets.MODID,
            "textures/particle/cross.png");
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
            guiGraphics.blit(UNSEEN,x-34,0,0,0,32,32,
                    32,32);
        } else {
            guiGraphics.blit(SEEN, x - 34, 0, 0, 0, 32, 32,
                    32, 32);
        }
        if (ClientStealthData.getPlayerStealth() < 0) {
            guiGraphics.blit(THREAT_1, x - 38, 0, 0, 0, 16, 16,
                    16, 16);
        }
        Player player = Minecraft.getInstance().player;
        float rot = player.getVisualRotationYInDegrees()*-1;
        Math.floor(player.getVisualRotationYInDegrees()/270);
        guiGraphics.blit(COMPASS,x-(width/2)-185,7,0,0,380,30,
                380,30);
        int north = (int) (x-(width/2)+rot+Math.floor((rot)/360)*-360)-180;
        int south = (int) (x-(width/2)+rot-180+Math.floor((rot-180)/360)*-360)-180;
        int east = (int) (x-(width/2)+rot-270+Math.floor((rot-270)/360)*-360)-180;
        int west = (int) (x-(width/2)+rot-90+Math.floor((rot-90)/360)*-360)-180;
        guiGraphics.drawCenteredString(gui.getFont(),"S",south+5,16,getColor(south, width));
        guiGraphics.drawCenteredString(gui.getFont(),"W", west+5,16,getColor(west, width));
        guiGraphics.drawCenteredString(gui.getFont(),"E",east+5,16,getColor(east, width));
        guiGraphics.drawCenteredString(gui.getFont(),"N",north+5,16,getColor(north, width));
    });
    public static int getColor(int data, int width) {
        int pos = data-width/2;
        final float alpha =Math.max(Math.min((80f/(Math.abs((float)pos)-105))-1,1),0);
        return toHex(new Color(1.0f, 1f ,1f ,alpha));
    }
    protected static int toHex(Color col) {
        String as = pad(Integer.toHexString(col.getAlpha()));
        String rs = pad(Integer.toHexString(col.getRed()));
        String gs = pad(Integer.toHexString(col.getGreen()));
        String bs = pad(Integer.toHexString(col.getBlue()));
        String hex = "0x" + as + rs + gs + bs;
        return Long.decode(hex).intValue();
    }

    private static String pad(String s) {
        return (s.length() == 1) ? "0" + s : s;
    }
}
