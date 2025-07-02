package dev.smootheez.scl.gui.widget.entry;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class SliderWidgetEntry<T extends Number> extends LabeledWidgetEntry<T> {
    protected final ConfigSliderWidget slider;

    public SliderWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<T> option, SliderMode mode) {
        super(label, description, option);

        double min = option.getMinValue().doubleValue();
        double max = option.getMaxValue().doubleValue();
        double initial = option.getValue().doubleValue();

        double step = switch (mode) {
            case INTEGER -> 1.0;
            case PERCENTAGE -> 0.01;
            case DECIMAL -> 0.1;
        };

        this.slider = new ConfigSliderWidget(
                0, 0, 80, 20,
                min, max, initial,
                step, mode,
                value -> {
                    T newValue = castToType(value);
                    option.setValue(newValue);
                    ConfigRegistry.markConfigAsDirty();
                    updateResetButton();
                }
        );

        this.children.add(this.slider);
        updateResetButton();
    }

    @Override
    public void resetButtonAction() {
        T defaultValue = option.getDefaultValue();
        option.setValue(defaultValue);
        slider.setSliderValue(defaultValue.doubleValue());
        ConfigRegistry.markConfigAsDirty();
        updateResetButton();
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
            throw new IllegalStateException("Unsupported type: " + option.getDefaultValue().getClass());
        }
    }
}

