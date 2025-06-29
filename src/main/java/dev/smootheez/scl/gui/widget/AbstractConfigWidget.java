package dev.smootheez.scl.gui.widget;

import net.minecraft.client.gui.components.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public abstract class AbstractConfigWidget extends ContainerObjectSelectionList.Entry<AbstractConfigWidget> {
    @Nullable
    final List<FormattedCharSequence> description;

    protected AbstractConfigWidget(@Nullable List<FormattedCharSequence> description) {
        this.description = description;
    }
}
