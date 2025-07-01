package dev.smootheez.scl.gui.widget.handler;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
import dev.smootheez.scl.gui.widget.entry.*;
import dev.smootheez.scl.gui.widget.entry.options.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class SliderDoubleWidgetHandler implements WidgetHandler<Double>{
    @Override
    public ConfigWidgetEntry createWidget(ConfigOption<Double> option, @Nullable List<FormattedCharSequence> description) {
        return new SliderDoubleWidgetEntry(Component.translatable(option.getTranslation()), description, option, SliderMode.DECIMAL);
    }
}
