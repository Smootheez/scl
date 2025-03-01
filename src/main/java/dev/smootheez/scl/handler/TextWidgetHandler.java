package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.ConfigOptionList;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import dev.smootheez.scl.widget.TextConfigWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

public class TextWidgetHandler implements WidgetHandler<ConfigOptionList> {
    @Override
    public AbstractConfigWidget createWidget(ConfigOption<ConfigOptionList> option, List<OrderedText> description) {
        return new TextConfigWidget(Text.translatable(option.getTranslation()), description, option);
    }
}
