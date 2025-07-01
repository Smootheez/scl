package dev.smootheez.scl.config;

import dev.smootheez.scl.*;
import dev.smootheez.scl.config.serializer.*;
import dev.smootheez.scl.config.serializer.options.*;
import dev.smootheez.scl.gui.widget.handler.*;

import java.util.*;

public class ConfigOption<T> {
    private final String key;
    private final T defaultValue;
    private T value;
    private T maxValue;
    private T minValue;

    private final Class<T> type;
    private final ConfigSerializer<T> serializer;
    private final WidgetHandler<T> widgetHandler;
    private String configIdentifier;

    protected ConfigOption(String key, T defaultValue, Class<T> type, ConfigSerializer<T> serializer, WidgetHandler<T> widgetHandler) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.type = type;
        this.serializer = serializer;
        this.value = defaultValue;
        this.widgetHandler = widgetHandler;
    }

    protected ConfigOption(String key, T defaultValue, T minValue, T maxValue, Class<T> type, ConfigSerializer<T> serializer, WidgetHandler<T> widgetHandler) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.type = type;
        this.serializer = serializer;
        this.value = defaultValue;
        this.widgetHandler = widgetHandler;
    }

    public static ConfigOption<Boolean> create(String key, Boolean defaultValue) {
        return new ConfigOption<>(key, defaultValue, Boolean.class, new BooleanSerializer(), new BooleanWidgetHandler());
    }

    public static ConfigOption<Integer> create(String key, Integer defaultValue, Integer minValue, Integer maxValue) {
        return new ConfigOption<>(key, defaultValue, minValue, maxValue, Integer.class, new IntegerSerializer(), new IntegerWidgetHandler());
    }

    public static ConfigOption<Double> create(String key, Double defaultValue, Double minValue, Double maxValue) {
        return new ConfigOption<>(key, defaultValue, minValue, maxValue, Double.class, new DoubleSerializer(), new DoubleWidgetHandler());
    }

    public static ConfigOption<OptionList> create(String key, String... defaultValue) {
        return new ConfigOption<>(key, new OptionList(Arrays.asList(defaultValue)), OptionList.class, new OptionListSerializer(), new OptionListWidgetHandler());
    }

    public static <E extends Enum<E>> ConfigOption<E> create(String key, E defaultValue) {
        Class<E> enumClass = getEnumClass(defaultValue);
        return new ConfigOption<>(key, defaultValue, enumClass, new EnumSerializer<>(enumClass), new CycleWidgetHandler<>());
    }

    @SuppressWarnings("unchecked")
    private static <E extends Enum<E>> Class<E> getEnumClass(E enumValue) {
        return (Class<E>) enumValue.getClass();
    }

    public String getKey() {
        return key;
    }

    public String getTranslation() {
        return "config." + getConfigIdentifier() + "." + key;
    }

    public Class<T> getType() {
        return type;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public T getValue() {
        return value;
    }

    public T getMaxValue() {
        return maxValue;
    }

    public T getMinValue() {
        return minValue;
    }

    public void setValue(T value) {
        if (type.isInstance(value))
            this.value = value;
        else Constants.LOGGER.error("Value {} is not of type {}", value, type);
    }

    public String getConfigIdentifier() {
        return configIdentifier;
    }

    public void setConfigIdentifier(String configIdentifier) {
        this.configIdentifier = configIdentifier;
    }

    public ConfigSerializer<T> getSerializer() {
        return serializer;
    }
}
