package me.wolfii.fabricforcelowercase.mixin;

import me.wolfii.fabricforcelowercase.StringHelper;
import net.minecraft.client.gui.components.EditBox;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EditBox.class)
public class TextFieldWidgetMixin {
    @Shadow
    private String value;

    @Inject(method = "getValue", at = @At("HEAD"), cancellable = true)
    private void modifiedGetText(CallbackInfoReturnable<String> cir) {
        if (this.value.startsWith("/")) {
            cir.setReturnValue(StringHelper.firstArgumentToLowerCase(this.value));
        }
    }

    @Redirect(method = "extractWidgetRenderState", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/components/EditBox;value:Ljava/lang/String;", opcode = Opcodes.GETFIELD))
    private String modifiedGetText(EditBox instance) {
        String finalText = this.value;
        if (this.value.startsWith("/")) {
            finalText = StringHelper.firstArgumentToLowerCase(value);
        }
        return finalText;
    }
}
