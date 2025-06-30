package dev.smootheez.scl.gui.widget.handler;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public interface WidgetHandler<T> {
    ConfigWidgetEntry createWidget(ConfigOption<T> option, @Nullable List<FormattedCharSequence> description);
}
