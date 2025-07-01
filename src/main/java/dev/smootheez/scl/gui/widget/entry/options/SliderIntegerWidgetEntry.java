package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class SliderIntegerWidgetEntry extends SliderWidgetEntry<Integer> {
    public SliderIntegerWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<Integer> option) {
        super(label, description, option);
    }
}
