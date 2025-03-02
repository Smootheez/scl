package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.OptionList;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import dev.smootheez.scl.widget.entry.TextConfigEntries;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Handles the creation of configuration GUI widgets for lists of configuration options.
 * This widget handler creates a TextConfigWidget which provides a display
 * and management interface for a list of ConfigOption objects within the configuration.
 */
public class TextWidgetHandler implements WidgetHandler<OptionList> {

    /**
     * Creates and returns a new TextConfigWidget instance for the given ConfigOption.
     * The widget will display a list of configuration options and provide
     * functionality to add, remove, and modify entries in the list.
     *
     * @param option   The configuration option containing a list of ConfigOption objects
     * @param description The list of descriptions to be displayed below the widget
     * @return A new TextConfigWidget instance
     */
    @Override
    public AbstractConfigWidget createWidget(ConfigOption<OptionList> option, @Nullable List<OrderedText> description) {
        return new TextConfigEntries(Text.translatable(option.getTranslation()), description, option);
    }
}
