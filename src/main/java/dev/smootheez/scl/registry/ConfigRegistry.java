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
 * Manages the registration, storage, and retrieval of configuration options for a mod.
 * This class acts as a central registry for configuration providers and their associated options.
 * It uses reflection to identify and process fields annotated with {@link ConfigOption}.
 *
 * <p>Configuration providers must implement {@link ConfigProvider} and be annotated with
 * {@link Config}. The registry handles:
 * <ul>
 *     <li>Registering configuration providers</li>
 *     <li>Storing and retrieving configuration options</li>
 *     <li>Handling configuration file operations through {@link ConfigFileWriter}</li>
 * </ul>
 *
 * @see Config
 * @see ConfigProvider
 * @see ConfigOption
 * @see ConfigFileWriter
 */
public class ConfigRegistry {
    private static final List<ConfigOption<?>> configOptions = new ArrayList<>();
    private static String configName;
    private static final Map<Class<? extends ConfigProvider>, ConfigFileWriter> configWriters = new HashMap<>();
    private static ConfigProvider configProvider;

    /**
     * Registers a configuration provider with the registry.
     * The provider must be annotated with {@link Config}.
     * <p>
     * This method:
     * <ul>
     *     <li>Sets the configuration name from the {@link Config} annotation</li>
     *     <li>Processes fields annotated with {@link ConfigOption}</li>
     *     <li>Initializes a {@link ConfigFileWriter} for file operations</li>
     * </ul>
     *
     * @param config the configuration provider to register
     * @throws IllegalArgumentException if the provider is not annotated with {@link Config}
     */
    public static <T extends ConfigProvider> void registerConfig(T config){
        var configClass = config.getClass();
        if (configClass.getAnnotation(Config.class) != null) {
            configName = configClass.getAnnotation(Config.class).value();
            configProvider = config;
            try {
                List<Field> configFields = getConfigFields(configClass);
                for (Field field : configFields) {
                    field.setAccessible(true);
                    ConfigOption<?> option = (ConfigOption<?>) field.get(config);
                    option.setConfigName(configName);
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
     * Saves the current configuration to file.
     * Uses the associated {@link ConfigFileWriter} for the registered configuration provider.
     *
     * @throws IllegalArgumentException if no configuration provider is registered
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
     * Retrieves all fields of the specified class that are annotated with {@link ConfigOption}.
     * @param clazz the class to inspect
     * @return list of fields annotated with {@link ConfigOption}
     */
    private static List<Field> getConfigFields(Class<?> clazz) {
        List<Field> configFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().equals(ConfigOption.class)) configFields.add(field);
        }
        return configFields;
    }

    /**
     * Returns the configuration name associated with the registered provider.
     * @return the configuration name
     */
    public static String getConfigName() {
        return configName;
    }

    /**
     * Returns the list of all registered configuration options.
     * @return list of configuration options
     */
    public static List<ConfigOption<?>> getConfigOptions() {
        return configOptions;
    }
}
