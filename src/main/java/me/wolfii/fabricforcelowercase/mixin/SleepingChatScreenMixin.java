package me.wolfii.fabricforcelowercase.mixin;

import me.wolfii.fabricforcelowercase.StringHelper;
import net.minecraft.client.gui.screen.SleepingChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SleepingChatScreen.class)
public class SleepingChatScreenMixin {
    @ModifyArg(method = { "keyPressed" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/SleepingChatScreen;sendMessage(Ljava/lang/String;Z)Z"), index = 0)
    private String result(final String text) {
        if (text.startsWith("/")) {
            return StringHelper.firstArgumentToLowerCase(text);
        }
        return text;
    }
}