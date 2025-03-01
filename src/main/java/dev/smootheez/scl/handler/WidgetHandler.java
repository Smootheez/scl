package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import net.minecraft.text.OrderedText;

import java.util.List;

/**
 * Interface for handling the creation of configuration widgets.
 * Implementations should create and configure GUI widgets that allow users
 * to interact with and modify configuration options.
 */
public interface WidgetHandler<T> {
    /**
     * Creates a new configuration widget for the specified option.
     * @param option The configuration option this widget represents
     * @param description The description text to display for this option
     * @return An instance of AbstractConfigWidget that provides the GUI
     *         for modifying the configuration option's value
     */
    AbstractConfigWidget createWidget(ConfigOption<T> option, List<OrderedText> description);
}
