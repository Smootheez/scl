package dev.smootheez.scl.widget;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.util.ValidateConfigValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IntConfigWidget extends NamedConfigWidget{
    private final TextFieldWidget textFieldWidget;
    private final ButtonWidget resetButton;
    private final ConfigOption<Integer> option;

    public IntConfigWidget(Text name, @Nullable List<OrderedText> description, ConfigOption<Integer> option) {
        super(name, description);
        this.option = option;

        textFieldWidget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 76, 16, name);
        textFieldWidget.setText(Integer.toString(option.getValue()));
        textFieldWidget.setChangedListener(this::onTextChanged);

        resetButton = ButtonWidget.builder(Text.of("⭮"), button -> resetValue())
                .dimensions(0, 0, 20, 20)
                .build();

        this.children.add(textFieldWidget);
        this.children.add(resetButton);

        updateResetButtonState();
    }

    private void onTextChanged(String value) {
        if (ValidateConfigValue.validateIntValue(value) && Integer.parseInt(value) >= option.getMinValue() && Integer.parseInt(value) <= option.getMaxValue()) {
            textFieldWidget.setEditableColor(14737632);
            option.setValue(Integer.valueOf(value));
        } else textFieldWidget.setEditableColor(16711680);
        updateResetButtonState();
    }

    private void resetValue() {
        int defaultValue = option.getDefaultValue();
        textFieldWidget.setText(Integer.toString(defaultValue));
        option.setValue(defaultValue);
        textFieldWidget.setEditableColor(14737632);
        updateResetButtonState();
    }

    private void updateResetButtonState() {
        int currentValue = option.getValue();
        int defaultValue = option.getDefaultValue();
        resetButton.active = currentValue != defaultValue;
    }

    @Override
    public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.drawName(context, x, y);

        this.textFieldWidget.setX(x + entryWidth - 103);
        this.textFieldWidget.setY(y + 2);
        this.textFieldWidget.render(context, mouseX, mouseY, tickDelta);

        this.resetButton.setX(x + entryWidth - 20);
        this.resetButton.setY(y);
        this.resetButton.render(context, mouseX, mouseY, tickDelta);
    }
}
