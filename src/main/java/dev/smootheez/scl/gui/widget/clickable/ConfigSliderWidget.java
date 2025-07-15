package dev.smootheez.scl.gui.widget.clickable;

import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

import java.util.function.*;

public class ConfigSliderWidget extends AbstractSliderButton {
    private final double min;
    private final double max;
    private final double step;
    private final SliderMode mode;
    private final Consumer<Double> onValueChanged;

    public ConfigSliderWidget(
            int x, int y, int width, int height,
            double min, double max, double initial,
            double step, SliderMode mode,
            Consumer<Double> onValueChanged) {
        super(x, y, width, height, Component.empty(), (initial - min) / (max - min));
        this.min = min;
        this.max = max;
        this.step = step;
        this.mode = mode;
        this.onValueChanged = onValueChanged;
        updateMessage();
    }

    @Override
    protected void updateMessage() {
        double snapped = snapValue(getActualValue());
        this.setMessage(Component.literal(formatValue(snapped)));
    }

    @Override
    protected void applyValue() {
        double snapped = snapValue(getActualValue());
        onValueChanged.accept(snapped);
        updateMessage();
    }

    public double getActualValue() {
        return min + (max - min) * this.value;
    }

    public void setSliderValue(double actualValue) {
        this.value = (actualValue - min) / (max - min);
        updateMessage();
    }

    private double snapValue(double value) {
        if (step <= 0) return value;
        return Math.round(value / step) * step;
    }

    private String formatValue(double value) {
        return switch (mode) {
            case INTEGER -> Integer.toString((int) value);
            case PERCENTAGE -> {
                double percent = (value - min) / (max - min);
                yield String.format("%.0f%%", percent * 100);
            }
            case DECIMAL -> String.format("%.2f", value);
        };
    }
}

