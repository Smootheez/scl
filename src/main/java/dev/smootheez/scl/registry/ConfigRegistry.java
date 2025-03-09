package dev.smootheez.scl.registry;

import dev.smootheez.scl.annotation.Config;
import dev.smootheez.scl.api.ConfigProvider;
import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.file.ConfigFileWriter;

import java.lang.reflect.Field;
import java.util.*;

public class ConfigRegistry {
    private static final Map<String, List<ConfigOption<?>>> configOptionsMap = new HashMap<>();
    private static final Set<String> usedConfigIdentifiers = new HashSet<>();
    private static final Map<String, ConfigFileWriter> configWriters = new HashMap<>();

    public static void registerConfig(Class<? extends ConfigProvider> config) {
        if (config.getAnnotation(Config.class) != null) {
            String configIdentifier = config.getAnnotation(Config.class).value();
            if (usedConfigIdentifiers.contains(configIdentifier)) {
                throw new IllegalArgumentException("Config identifier '" + configIdentifier + "' is already in use.");
            }
            usedConfigIdentifiers.add(configIdentifier);
            try {
                List<Field> configFields = getConfigFields(config);
                List<ConfigOption<?>> options = new ArrayList<>();
                for (Field field : configFields) {
                    field.setAccessible(true);
                    ConfigOption<?> option = (ConfigOption<?>) field.get(config);
                    option.setConfigIdentifier(configIdentifier);
                    options.add(option);
                }
                configOptionsMap.put(configIdentifier, options);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ConfigFileWriter fileWriter = new ConfigFileWriter(configIdentifier);
            configWriters.put(configIdentifier, fileWriter);
            fileWriter.loadConfig();
        } else throw new IllegalArgumentException("ConfigFileWriter must be annotated with @Config");
    }

    public static void save() {
        for (ConfigFileWriter writer : configWriters.values()) {
            writer.saveConfig();
        }
    }

    private static List<Field> getConfigFields(Class<?> clazz) {
        List<Field> configFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().equals(ConfigOption.class)) configFields.add(field);
        }
        return configFields;
    }

    public static List<ConfigOption<?>> getConfigOptions(String configIdentifier) {
        return configOptionsMap.getOrDefault(configIdentifier, new ArrayList<>());
    }
}
