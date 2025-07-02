package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class TextIntegerWidgetEntry extends TextWidgetEntry<Integer> {
    public TextIntegerWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<Integer> option) {
        super(label, description, option);
    }

    @Override
    public Integer getValue() {
        String val = editBox.getValue();
        if (val.isEmpty()) {
            return option.getValue(); // or return a safe default
        }
        return Integer.parseInt(val);
    }

    @Override
    protected void onTextChange(String value) {
        if (validateInteger(value) && Integer.parseInt(value) >= option.getMinValue() && Integer.parseInt(value) <= option.getMaxValue()) {
            option.setValue(Integer.valueOf(value));
            editBox.setTextColor(14737632);
        } else editBox.setTextColor(16736352);
        super.onTextChange(value);
    }

    private boolean validateInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
