package dev.smootheez.scl.gui.widget.entry;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class SliderWidgetEntry<T extends Number> extends LabeledWidgetEntry {
    protected final ConfigOption<T> option;
    protected final ConfigSliderWidget slider;

    public SliderWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<T> option) {
        super(label, description);
        this.option = option;

        double min = option.getMinValue().doubleValue();
        double max = option.getMaxValue().doubleValue();
        double initial = option.getValue().doubleValue();

        this.slider = new ConfigSliderWidget(
                0, 0, 80, 20,
                min, max, initial,
                this::formatValue,
                value -> {
                    T val = castToType(value);
                    option.setValue(val);
                    updateResetButton();
                }
        );

        this.children.add(this.slider);
        updateResetButton();
    }

    @Override
    public void updateResetButton() {
        getResetButton().active = !option.getValue().equals(option.getDefaultValue());
    }

    @Override
    public void resetButtonAction() {
        T defaultValue = option.getDefaultValue();
        option.setValue(defaultValue);
        slider.setSliderValue(defaultValue.doubleValue());
        updateResetButton();
    }

    private String formatValue(double value) {
        if (option.getValue() instanceof Integer) return Integer.toString((int) value);
        return String.format("%.2f", value);
    }

    @SuppressWarnings("unchecked")
    private T castToType(double value) {
        if (option.getDefaultValue() instanceof Integer) {
            return (T) Integer.valueOf((int) value);
        } else if (option.getDefaultValue() instanceof Float) {
            return (T) Float.valueOf((float) value);
        } else if (option.getDefaultValue() instanceof Double) {
            return (T) Double.valueOf(value);
        } else {
            throw new IllegalStateException("Unsupported number type: " + option.getDefaultValue().getClass());
        }
    }
}

