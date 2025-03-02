package dev.smootheez.scl.handler;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.OptionList;
import dev.smootheez.scl.widget.AbstractConfigWidget;
import dev.smootheez.scl.widget.entry.OptionListConfigEntries;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OptionListWidgetHandler implements WidgetHandler<OptionList> {
    @Override
    public AbstractConfigWidget createWidget(ConfigOption<OptionList> option, @Nullable List<OrderedText> description) {
        return new OptionListConfigEntries(Text.translatable(option.getTranslation()), description, option);
    }
}
