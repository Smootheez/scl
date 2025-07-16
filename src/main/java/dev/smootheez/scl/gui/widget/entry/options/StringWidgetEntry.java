package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class StringWidgetEntry extends TextWidgetEntry<String> {
    public StringWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<String> option) {
        super(label, description, option);
    }

    @Override
    protected void onTextChange(String value) {
        option.setValue(value);
        editBox.setTextColor(14737632);
        super.onTextChange(value);
    }

    @Override
    public String getValue() {
        return editBox.getValue();
    }
}
