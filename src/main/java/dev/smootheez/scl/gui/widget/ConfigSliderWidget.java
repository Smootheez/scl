package dev.smootheez.scl.gui.widget;

import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

import java.util.function.*;

public class ConfigSliderWidget extends AbstractSliderButton {
    private final double min;
    private final double max;
    private final Consumer<Double> onValueChanged;
    private final Function<Double, String> formatter;

    public ConfigSliderWidget(int x, int y, int width, int height, double min, double max, double initial,
                              Function<Double, String> formatter, Consumer<Double> onValueChanged) {
        super(x, y, width, height, Component.empty(), (initial - min) / (max - min));
        this.min = min;
        this.max = max;
        this.onValueChanged = onValueChanged;
        this.formatter = formatter;
        updateMessage();
    }

    @Override
    protected void updateMessage() {
        this.setMessage(Component.literal(formatter.apply(getActualValue())));
    }

    @Override
    protected void applyValue() {
        onValueChanged.accept(getActualValue());
        updateMessage();
    }

    public double getActualValue() {
        return min + (max - min) * this.value;
    }

    public void setSliderValue(double actualValue) {
        this.value = (actualValue - min) / (max - min);
        updateMessage();
    }
}

