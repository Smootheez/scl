package dev.smootheez.scl.widget.entry;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.helper.ConfigWidgetHelper;
import dev.smootheez.scl.widget.NamedConfigWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A configuration widget specifically designed to handle boolean values.
 * Provides a toggle button to cycle between true and false states,
 * and a reset button to revert to the default value.
 * Extends NamedConfigWidget to include a display name and optional description.
 *
 * @see NamedConfigWidget
 * @see ConfigOption
 */
public class BooleanConfigEntries extends NamedConfigWidget {
    private final CyclingButtonWidget<Boolean> toggleButton;
    private final ButtonWidget resetButton;
    private final ConfigOption<Boolean> option;

    /**
     * Constructs a new BooleanConfigWidget with the specified parameters.
     *
     * @param name        The display name of the configuration option
     * @param description Optional description text to be displayed below the option
     * @param option     The boolean configuration option being controlled
     */
    public BooleanConfigEntries(Text name, @Nullable List<OrderedText> description, ConfigOption<Boolean> option) {
        super(name, description);
        this.option = option;

        this.toggleButton = CyclingButtonWidget.onOffBuilder(option.getValue())
                .omitKeyText()
                .build(0, 0, 80, 20, name, ((button, value) -> {
                    option.setValue(value);
                    updateResetButtonState();
                }));

        resetButton = ConfigWidgetHelper.createResetButton(this::resetValue);

        this.children.add(this.toggleButton);
        this.children.add(this.resetButton);

        updateResetButtonState();
    }

    /**
     * Resets the configuration option to its default value.
     * Updates both the toggle button state and the reset button state.
     */
    private void resetValue() {
        boolean defaultValue = option.getDefaultValue();
        option.setValue(defaultValue);
        toggleButton.setValue(defaultValue);
        updateResetButtonState();
    }

    /**
     * Updates the reset button's active state based on whether the current value
     * differs from the default value.
     */
    private void updateResetButtonState() {
        boolean currentValue = option.getValue();
        boolean defaultValue = option.getDefaultValue();
        resetButton.active = currentValue != defaultValue;
    }

    @Override
    public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.drawName(context, x, y);

        this.toggleButton.setX(x + entryWidth - 105);
        this.toggleButton.setY(y);
        this.toggleButton.render(context, mouseX, mouseY, tickDelta);

        ConfigWidgetHelper.setResetButtonPosition(this.resetButton, context, x, y, entryWidth, mouseX, mouseY, tickDelta);
    }
}
