package dev.smootheez.scl.widget;

import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.text.OrderedText;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractConfigWidget extends ElementListWidget.Entry<AbstractConfigWidget> {
    @Nullable
    final List<OrderedText> description;

    protected AbstractConfigWidget(@Nullable List<OrderedText> description) {
        this.description = description;
    }
}
