package dev.smootheez.scl.gui.widget.handler;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.clickable.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class SliderWidgetHandler<T extends Number> implements WidgetHandler<T> {
    private final SliderMode mode;

    public SliderWidgetHandler(SliderMode mode) {
        this.mode = mode;
    }

    @Override
    public ConfigWidgetEntry createWidget(ConfigOption<T> option, @Nullable List<FormattedCharSequence> description) {
        return new SliderWidgetEntry<>(Component.translatable(option.getTranslation()), description, option, mode);
    }
}
