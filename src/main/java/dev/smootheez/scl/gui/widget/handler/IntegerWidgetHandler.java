package dev.smootheez.scl.gui.widget.handler;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import dev.smootheez.scl.gui.widget.entry.options.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class IntegerWidgetHandler implements WidgetHandler<Integer> {
    @Override
    public ConfigWidgetEntry createWidget(ConfigOption<Integer> option, @Nullable List<FormattedCharSequence> description) {
        return new IntegerWidgetEntry(Component.translatable(option.getTranslation()), description, option);
    }
}
