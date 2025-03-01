package dev.smootheez.scl.registry;

import dev.smootheez.scl.annotation.Config;
import dev.smootheez.scl.api.ConfigProvider;
import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.file.ConfigFileWriter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ConfigRegistry class manages the registration, saving, and retrieval of configuration options
 * for a mod. It uses reflection to identify fields annotated with {@link ConfigOption} and associates
 * them with a specific mod ID. The class provides methods to register a configuration provider,
 * save the current configuration, and retrieve configuration options.
 *
 * <p>The configuration provider must be annotated with {@link Config} to be registered. The
 * {@link ConfigFileWriter} is responsible for loading and saving the configuration to a file.
 *
 * @see Config
 * @see ConfigProvider
 * @see ConfigOption
 * @see ConfigFileWriter
 */
public class ConfigRegistry {
    private static final List<ConfigOption<?>> configOptions = new ArrayList<>();
    private static String modId;
    private static final Map<Class<? extends ConfigProvider>, ConfigFileWriter> configWriters = new HashMap<>();
    private static ConfigProvider configProvider;

    /**
     * Registers a configuration provider with the registry. The provider must be annotated with {@link Config}.
     * This method sets the mod ID, processes fields annotated with {@link ConfigOption}, and initializes
     * a {@link ConfigFileWriter} to handle configuration file operations.
     *
     * @param config the configuration provider to register
     * @throws IllegalArgumentException if the configuration provider is not annotated with {@link Config}
     */
    public static <T extends ConfigProvider> void registerConfig(T config){
        var configClass = config.getClass();
        if (configClass.getAnnotation(Config.class) != null) {
            modId = configClass.getAnnotation(Config.class).value();
            configProvider = config;
            try {
                List<Field> configFields = getConfigFields(configClass);
                for (Field field : configFields) {
                    field.setAccessible(true);
                    ConfigOption<?> option = (ConfigOption<?>) field.get(config);
                    option.setModId(modId);
                    configOptions.add(option);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ConfigFileWriter fileWriter = new ConfigFileWriter();
            configWriters.put(config.getClass(), fileWriter);
            fileWriter.loadConfig();
        } else throw new IllegalArgumentException("ConfigFileWriter must be annotated with @Config");
    }

    /**
     * Saves the current configuration to a file using the associated {@link ConfigFileWriter}.
     * Throws an exception if no configuration provider is registered or if the writer is not found.
     *
     * @throws IllegalArgumentException if no config provider is registered or if the writer is not found
     */
    public static void save() {
        if (configProvider != null) {
            ConfigFileWriter writer = configWriters.get(configProvider.getClass());
            if (writer != null) writer.saveConfig();
            else throw new IllegalArgumentException("Config class not registered: " + configProvider.getClass().getName());
        } else {
            throw new IllegalArgumentException("No config provider registered.");
        }
    }

    /**
     * Retrieves all fields of the specified class that are of type {@link ConfigOption}.
     *
     * @param clazz the class to inspect
     * @return a list of fields of type {@link ConfigOption}
     */
    private static List<Field> getConfigFields(Class<?> clazz) {
        List<Field> configFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().equals(ConfigOption.class)) configFields.add(field);
        }
        return configFields;
    }

    /**
     * Returns the mod ID associated with the registered configuration provider.
     *
     * @return the mod ID
     */
    public static String getModId() {
        return modId;
    }

    /**
     * Returns the list of all registered configuration options.
     *
     * @return the list of configuration options
     */
    public static List<ConfigOption<?>> getConfigOptions() {
        return configOptions;
    }
}
