package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import dev.smootheez.scl.widget.DoubleConfigWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

/**
 * Handles the creation of configuration GUI widgets for Double values.
 * This widget handler creates a DoubleConfigWidget which provides a slider
 * and input field for precise control of double values within the configuration.
 */
public class DoubleWidgetHandler implements WidgetHandler<Double> {

    /**
     * Creates and returns a new DoubleConfigWidget instance for the given ConfigOption.
     * The widget will display a slider and input field for the Double value,
     * allowing users to either slide to select a value or input it directly.
     *
     * @param option   The configuration option to create the widget for
     * @param description The list of descriptions to be displayed below the widget
     * @return A new DoubleConfigWidget instance
     */
    @Override
    public AbstractConfigWidget createWidget(ConfigOption<Double> option, List<OrderedText> description) {
        return new DoubleConfigWidget(Text.translatable(option.getTranslation()), description, option);
    }
}

