package dev.sqaw.i_like_bugs_btw.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class I_like_bugs_btwClient implements ClientModInitializer {
    public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            net.minecraft.resources.Identifier.fromNamespaceAndPath("i_like_bugs_btw", "binds")
    );

    private static KeyMapping keyBinding = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.i_like_bugs_btw.bind",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            CATEGORY
    ));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.consumeClick()) {
                if (client.player == null) return;
                i_like_bugs_btwHandler.toggle();
                String color = i_like_bugs_btwHandler.getFlag() ? "§a" : "§c";
                client.player.displayClientMessage(
                        Component.literal(color + Component.translatable(i_like_bugs_btwHandler.getFlag() ? "message.i_like_bugs_btw.toggle_ghosting.on" : "message.i_like_bugs_btw.toggle_ghosting.off").getString()),
                        true
                );
            }
        });
    }
}