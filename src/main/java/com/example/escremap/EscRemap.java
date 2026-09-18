package com.example.escremap;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.lwjgl.glfw.GLFW;

@Mod(value = "esc_remap", dist = Dist.CLIENT)
public class EscRemap {

    private static final KeyMapping ESC_KEY = new KeyMapping(
            "key.esc_remap.alternative_esc",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_F12,
            "key.categories.esc_remap"
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ESC_KEY);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();

        while (ESC_KEY.consumeClick()) {
            if (mc.screen != null) {
                mc.setScreen(null);
            } else {
                mc.pauseGame(false);
            }
        }
    }
}

