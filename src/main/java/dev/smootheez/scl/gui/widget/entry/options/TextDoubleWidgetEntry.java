package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class TextDoubleWidgetEntry extends TextWidgetEntry<Double> {
    public TextDoubleWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<Double> option) {
        super(label, description, option);
    }

    @Override
    protected void onTextChange(String value) {
        if (validateDuble(value) && Double.parseDouble(value) >= option.getMinValue() && Double.parseDouble(value) <= option.getMaxValue()) {
            option.setValue(Double.valueOf(value));
            editBox.setTextColor(-2039584);
        } else editBox.setTextColor(-65536);
        super.onTextChange(value);
    }

    @Override
    public Double getValue() {
        try {
            return Double.parseDouble(editBox.getValue());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean validateDuble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
