package org.modogthedev.volcanobay.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.modogthedev.volcanobay.Volcanobay;

public class StealthHudOverlay implements HudRenderCallback {
    private static final Identifier SEEN = new Identifier(Volcanobay.MOD_ID,
            "textures/stealth/seen.png");
    private static final Identifier UNSEEN = new Identifier(Volcanobay.MOD_ID,
            "textures/stealth/unseen.png");
    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width;
            y = height;
        }
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        context.drawTexture(SEEN,x-32,y-32,0,0,32,32,
                16,16);
        context.drawText();
    }
}
