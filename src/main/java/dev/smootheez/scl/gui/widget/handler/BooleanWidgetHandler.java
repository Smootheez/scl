package dev.smootheez.scl.gui.widget.handler;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import dev.smootheez.scl.gui.widget.entry.options.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class BooleanWidgetHandler implements WidgetHandler<Boolean> {
    @Override
    public ConfigWidgetEntry createWidget(ConfigOption<Boolean> option, @Nullable List<FormattedCharSequence> description) {
        return new BooleanWidgetEntry(Component.translatable(option.getTranslation()), description, option);
    }
}
