package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import dev.smootheez.scl.widget.CycleConfigWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

public class CycleWidgetHandler<E extends Enum<E>> implements WidgetHandler<E> {
    @Override
    public AbstractConfigWidget createWidget(ConfigOption<E> option, List<OrderedText> description) {
        return new CycleConfigWidget<>(Text.translatable(option.getTranslation()), description, option);
    }
}
