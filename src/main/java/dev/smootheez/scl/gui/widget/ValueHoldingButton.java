package dev.smootheez.scl.gui.widget;

import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

public class ValueHoldingButton<T> extends Button {
    private T value;

    public ValueHoldingButton(int i, int j, int k, int l, Component component, OnPress onPress, T initialValue) {
        super(i, j, k, l, component, onPress, DEFAULT_NARRATION);
        this.value = initialValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public static <T> Builder<T> builder(Component message, OnPress onPress, T initialValue) {
        return new Builder<>(message, onPress, initialValue);
    }

    // A custom Builder that extends the default Button.Builder
    public static class Builder<T> {
        private final Component message;
        private final OnPress onPress;
        private final T initialValue;
        private int x;
        private int y;
        private int width = 150; // Default width
        private int height = 20; // Default height

        public Builder(Component message, OnPress onPress, T initialValue) {
            this.message = message;
            this.onPress = onPress;
            this.initialValue = initialValue;
        }

        public Builder<T> pos(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder<T> size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public ValueHoldingButton<T> build() {
            return new ValueHoldingButton<>(this.x, this.y, this.width, this.height, this.message, this.onPress, this.initialValue);
        }
    }

}
