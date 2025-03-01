package dev.smootheez.scl.config;

import dev.smootheez.scl.config.option.ConfigOptionList;
import dev.smootheez.scl.handler.BooleanWidgetHandler;
import dev.smootheez.scl.handler.TextWidgetHandler;
import dev.smootheez.scl.handler.WidgetHandler;
import dev.smootheez.scl.serializer.BooleanSerializer;
import dev.smootheez.scl.serializer.ConfigOptionListSerializer;
import dev.smootheez.scl.serializer.ConfigSerializer;

import java.util.Arrays;

/**
 * Represents a generic configuration option with type safety and metadata.
 * <T> The type of the configuration value (e.g., Boolean, Integer, String)
 */
public class ConfigOption<T> {
    protected final String key;
    protected final T defaultValue;
    protected T value;
    protected T minValue;
    protected T maxValue;

    protected final Class<T> type;
    protected final ConfigSerializer<T> serializer;
    protected final WidgetHandler<T> widgetHandler;
    protected String translation;

    /**
     * Constructs a new ConfigOption instance with the specified key, default value,
     * and configuration metadata.
     * @param <T> The type of the configuration value
     * @param key The unique identifier for this configuration option
     * @param defaultValue The default value to use when no value is set
     * @param type The class representing the type of the configuration value
     * @param serializer The serializer used to convert values to/from JSON
     * @param widgetHandler The handler used to create the configuration GUI widget
     */
    public ConfigOption(String key, T defaultValue, Class<T> type, ConfigSerializer<T> serializer, WidgetHandler<T> widgetHandler) {
        this.key = key;
        this.value = defaultValue;
        this.defaultValue = defaultValue;
        this.type = type;
        this.serializer = serializer;
        this.widgetHandler = widgetHandler;
    }

    /**
     * Constructs a new ConfigOption instance with the specified key, default value,
     * and configuration metadata, including minimum and maximum values.
     * @param <T> The type of the configuration value
     * @param key The unique identifier for this configuration option
     * @param defaultValue The default value to use when no value is set
     * @param type The class representing the type of the configuration value
     * @param serializer The serializer used to convert values to/from JSON
     * @param widgetHandler The handler used to create the configuration GUI widget
     * @param minValue The minimum allowed value for this configuration option
     * @param maxValue The maximum allowed value for this configuration option
     */
    public ConfigOption(String key, T defaultValue, Class<T> type, ConfigSerializer<T> serializer, WidgetHandler<T> widgetHandler, T minValue, T maxValue) {
        this.key = key;
        this.value = defaultValue;
        this.defaultValue = defaultValue;
        this.type = type;
        this.serializer = serializer;
        this.widgetHandler = widgetHandler;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Returns the key of this configuration option.
     * @return the key of this configuration option
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the translation key for this configuration option to a value of the form
     * "options.$modId.$key". This translation key is typically used for localization
     * purposes in the configuration GUI.
     * @param modId The mod ID this configuration option belongs to
     * @return The constructed translation key
     */
    public String setTranslation(String modId) {
        this.translation = "options." + modId + "." + key;
        return translation;
    }

    /**
     * Retrieves the translation key for this configuration option.
     * @return the translation key as a String
     */
    public String getTranslation() {
        return translation;
    }

    /**
     * Retrieves the default value of this configuration option.
     * @return the default value of this configuration option
     */
    public T getDefaultValue() {
        return defaultValue;
    }

    /**
     * Retrieves the current value of this configuration option.
     * @return the current value of this configuration option
     */
    public T getValue() {
        return value;
    }

    /**
     * Retrieves the minimum value for this configuration option, if specified.
     * @return the minimum value of this configuration option, or null if not specified
     */
    public T getMinValue() {
        return minValue;
    }

    /**
     * Retrieves the maximum value for this configuration option, if specified.
     * @return the maximum value of this configuration option, or null if not specified
     */
    public T getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the value of this configuration option to the given value.
     * @param value the value to set
     * @throws IllegalArgumentException if the value is not of type T
     */
    public void setValue(T value) {
        if (type.isInstance(value)) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Value must be of type " + type.getSimpleName());
        }
    }

    /**
     * Retrieves the type of the configuration option.
     * @return the type of the configuration option
     */
    public Class<T> getType() {
        return type;
    }

    /**
     * Retrieves the widget handler associated with this configuration option.
     * The widget handler is responsible for creating and managing the GUI widget
     * used to edit this configuration option's value.
     * @return the widget handler associated with this configuration option
     */
    public WidgetHandler<T> getWidgetHandler() {
        return widgetHandler;
    }

    /**
     * Retrieves the serializer associated with this configuration option.
     * The serializer is responsible for converting the value to and from JSON
     * when reading/writing configuration files.
     * @return the serializer associated with this configuration option
     */
    public ConfigSerializer<T> getSerializer() {
        return serializer;
    }

    /**
     * Creates a new Boolean configuration option with the specified key and default value.
     * This convenience method uses default handlers for Boolean values.
     * @param key The unique identifier for this configuration option
     * @param defaultValue The default value to use when no value is set
     * @return A new Boolean configuration option instance
     */
    public static ConfigOption<Boolean> create(String key, Boolean defaultValue) {
        return new ConfigOption<>(key, defaultValue, Boolean.class, new BooleanSerializer(), new BooleanWidgetHandler());
    }

    public static ConfigOption<ConfigOptionList> create(String key, String... defaultValue) {
        return new ConfigOption<>(key, new ConfigOptionList(Arrays.asList(defaultValue)), ConfigOptionList.class, new ConfigOptionListSerializer(), new TextWidgetHandler());
    }
}
