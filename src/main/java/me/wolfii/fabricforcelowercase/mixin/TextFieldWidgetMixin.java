package me.wolfii.fabricforcelowercase.mixin;

import me.wolfii.fabricforcelowercase.StringHelper;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {
    @Shadow
    private String text;

    @Redirect(method = "write", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;text:Ljava/lang/String;", opcode = Opcodes.PUTFIELD))
    private void modifiedWriteInput(TextFieldWidget textFieldWidget, String text) {
        if (text.startsWith("/")) {
            text =  StringHelper.firstArgumentToLowerCase(text);
        }
        this.text = text;
    }
}
