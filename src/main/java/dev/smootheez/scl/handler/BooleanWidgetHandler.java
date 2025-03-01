package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import dev.smootheez.scl.widget.BooleanConfigWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

/**
 * Handles the creation of configuration widgets for boolean values.
 * This implementation creates a widget that allows users to toggle
 * a boolean configuration option between true and false states.
 */
public class BooleanWidgetHandler implements WidgetHandler<Boolean> {

    /**
     * Creates a new boolean configuration widget for the specified option.
     * The widget will display a checkbox that toggles the boolean value,
     * along with the option's description text.
     *
     * @param option The boolean configuration option this widget represents
     * @param description The description text to display for this option
     * @param modId The mod ID this configuration option belongs to
     * @return An AbstractConfigWidget instance that provides a checkbox
     *         for modifying the boolean option's value
     */
    @Override
    public AbstractConfigWidget createWidget(ConfigOption<Boolean> option, List<OrderedText> description, String modId) {
        return new BooleanConfigWidget(Text.translatable(option.setTranslation(modId)), description, option);
    }
}
