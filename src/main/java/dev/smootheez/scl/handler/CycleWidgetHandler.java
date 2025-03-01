package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import dev.smootheez.scl.widget.option.CycleConfigWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

/**
 * Handles the creation of configuration GUI widgets for Enum types.
 * This widget handler specializes in creating cycleable widgets that allow
 * users to switch between different Enum values through a cycled list.
 *
 * @param <E> The Enum type being handled
 */
public class CycleWidgetHandler<E extends Enum<E>> implements WidgetHandler<E> {

    /**
     * Creates and returns a new CycleConfigWidget instance for the given ConfigOption.
     * The widget will display the current Enum value and allow cycling through
     * all available values of the Enum type.
     *
     * @param option   The configuration option to create the widget for
     * @param description The list of descriptions to be displayed below the widget
     * @return A new CycleConfigWidget instance
     */
    @Override
    public AbstractConfigWidget createWidget(ConfigOption<E> option, List<OrderedText> description) {
        return new CycleConfigWidget<>(Text.translatable(option.getTranslation()), description, option);
    }
}
