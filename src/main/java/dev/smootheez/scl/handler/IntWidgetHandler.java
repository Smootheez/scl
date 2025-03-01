package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import dev.smootheez.scl.widget.option.IntConfigWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

/**
 * Handles the creation of configuration GUI widgets for Integer values.
 * This widget handler creates an IntConfigWidget which provides a slider
 * and input field for precise control of integer values within the configuration.
 */
public class IntWidgetHandler implements WidgetHandler<Integer> {

    /**
     * Creates and returns a new IntConfigWidget instance for the given ConfigOption.
     * The widget will display a slider and input field for the Integer value,
     * allowing users to either slide to select a value or input it directly.
     *
     * @param option   The configuration option to create the widget for
     * @param description The list of descriptions to be displayed below the widget
     * @return A new IntConfigWidget instance
     */
    @Override
    public AbstractConfigWidget createWidget(ConfigOption<Integer> option, List<OrderedText> description) {
        return new IntConfigWidget(Text.translatable(option.getTranslation()), description, option);
    }
}
