package dev.smootheez.scl.helper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class ConfigWidgetHelper {
    public static ButtonWidget createResetButton(Runnable action) {
        return ButtonWidget.builder(Text.of("⭮"), button -> action.run())
                .dimensions(0, 0, 20, 20).build();
    }

    public static TextFieldWidget createTextFieldWidget(Text name) {
        return new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 76, 16, name);
    }

    public static void setResetButtonPosition(ButtonWidget resetButton, DrawContext context, int x, int y, int entryWidth, int mouseX, int mouseY, float tickDelta) {
        resetButton.setX(x + entryWidth - 20);
        resetButton.setY(y);
        resetButton.render(context, mouseX, mouseY, tickDelta);
    }
}
