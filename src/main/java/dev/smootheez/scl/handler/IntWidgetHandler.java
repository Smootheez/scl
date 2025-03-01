package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import dev.smootheez.scl.widget.IntConfigWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

public class IntWidgetHandler implements WidgetHandler<Integer>{
    @Override
    public AbstractConfigWidget createWidget(ConfigOption<Integer> option, List<OrderedText> description) {
        return new IntConfigWidget(Text.translatable(option.getTranslation()), description, option);
    }
}
