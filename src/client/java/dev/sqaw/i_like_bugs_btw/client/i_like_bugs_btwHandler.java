package dev.sqaw.i_like_bugs_btw.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.glfw.GLFW;

public class i_like_bugs_btwHandler {
    private static boolean flag = false;
    private static int lastSetlectedHotbarSlot = 0;
    private static int offHandSlot, mainHandSlot;

    public static void afterAction() {
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;
        int syncId = player.inventoryMenu.containerId;

        offHandSlot = 45;
        mainHandSlot = 36 + player.getInventory().getSelectedSlot();
        if (player.getInventory().getSelectedSlot() == 0) {
            mainHandSlot = 36 + lastSetlectedHotbarSlot;
        }
        lastSetlectedHotbarSlot = player.getInventory().getSelectedSlot();
        Minecraft.getInstance().execute(() -> {
            Minecraft.getInstance().execute(() -> {
                KeyMapping.click(InputConstants.Type.KEYSYM.getOrCreate(GLFW.GLFW_KEY_F));
            });
        });
    }
    protected static void toggle() { flag = !flag;}
    public static boolean getFlag() {return flag;}
}