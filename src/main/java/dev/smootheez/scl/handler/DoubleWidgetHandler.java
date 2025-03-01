package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import dev.smootheez.scl.widget.DoubleConfigWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

public class DoubleWidgetHandler implements WidgetHandler<Double>{
    @Override
    public AbstractConfigWidget createWidget(ConfigOption<Double> option, List<OrderedText> description) {
        return new DoubleConfigWidget(Text.translatable(option.getTranslation()), description, option);
    }
}
