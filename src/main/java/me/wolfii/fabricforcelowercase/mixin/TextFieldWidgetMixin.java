package me.wolfii.fabricforcelowercase.mixin;

import me.wolfii.fabricforcelowercase.StringHelper;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {
    @Shadow
    private String text;

    @Inject(method = "getText", at = @At("HEAD"), cancellable = true)
    private void modifiedGetText(CallbackInfoReturnable<String> cir) {
        if (this.text.startsWith("/")) {
            cir.setReturnValue(StringHelper.firstArgumentToLowerCase(this.text));
        }
    }

    @Redirect(method = "renderButton", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;text:Ljava/lang/String;", opcode = Opcodes.GETFIELD))
    private String modifiedGetText(TextFieldWidget instance) {
        String finalText = this.text;
        if (this.text.startsWith("/")) {
            finalText = StringHelper.firstArgumentToLowerCase(text);
        }
        return finalText;
    }
}
