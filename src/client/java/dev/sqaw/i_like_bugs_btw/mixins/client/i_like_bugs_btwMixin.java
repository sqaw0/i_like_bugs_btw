package dev.sqaw.i_like_bugs_btw.mixins.client;

import dev.sqaw.i_like_bugs_btw.client.i_like_bugs_btwHandler;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class i_like_bugs_btwMixin {
    private static boolean alreadyPressed = false;

    @Inject(method = "handleKeybinds", at = @At("HEAD"))
    private void onHandleInputEvents(CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;

        if (player == null) return;

        boolean isPressed = client.options.keyUse.isDown();


        if (isPressed && !alreadyPressed) {
            if (!i_like_bugs_btwHandler.getFlag()) return;
            KeyMapping.click(InputConstants.Type.KEYSYM.getOrCreate(GLFW.GLFW_KEY_F));
            i_like_bugs_btwHandler.afterAction();
        }
        alreadyPressed = isPressed;
    }
}