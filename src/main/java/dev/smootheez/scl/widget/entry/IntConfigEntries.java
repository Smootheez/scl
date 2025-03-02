package dev.smootheez.scl.widget.entry;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.helper.ConfigWidgetHelper;
import dev.smootheez.scl.util.ValidateConfigValue;
import dev.smootheez.scl.widget.NamedConfigWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IntConfigEntries extends NamedConfigWidget {
    private final TextFieldWidget textFieldWidget;
    private final ButtonWidget resetButton;
    private final ConfigOption<Integer> option;

    public IntConfigEntries(Text name, @Nullable List<OrderedText> description, ConfigOption<Integer> option) {
        super(name, description);
        this.option = option;

        textFieldWidget = ConfigWidgetHelper.createTextFieldWidget(name);
        textFieldWidget.setText(Integer.toString(option.getValue()));
        textFieldWidget.setChangedListener(this::onTextChanged);

        resetButton = ConfigWidgetHelper.createResetButton(this::resetValue);

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

        ConfigWidgetHelper.setResetButtonPosition(this.resetButton, context, x, y, entryWidth, mouseX, mouseY, tickDelta);
    }
}
