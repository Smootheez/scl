package dev.smootheez.scl.registry;

import dev.smootheez.scl.annotation.Config;
import dev.smootheez.scl.api.ConfigProvider;
import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.file.ConfigFileWriter;

import java.lang.reflect.Field;
import java.util.*;

public class ConfigRegistry {
    private static final Map<Class<? extends ConfigProvider>, List<ConfigOption<?>>> configOptionsMap = new HashMap<>();
    private static final Set<String> usedConfigIdentifiers = new HashSet<>();
    private static final Map<Class<? extends ConfigProvider>, ConfigFileWriter> configWriters = new HashMap<>();

    public static <T extends ConfigProvider> void registerConfig(T config) {
        var configClass = config.getClass();
        if (configClass.getAnnotation(Config.class) != null) {
            String configIdentifier = configClass.getAnnotation(Config.class).value();
            if (usedConfigIdentifiers.contains(configIdentifier)) {
                throw new IllegalArgumentException("Config identifier '" + configIdentifier + "' is already in use.");
            }
            usedConfigIdentifiers.add(configIdentifier);
            try {
                List<Field> configFields = getConfigFields(configClass);
                List<ConfigOption<?>> options = new ArrayList<>();
                for (Field field : configFields) {
                    field.setAccessible(true);
                    ConfigOption<?> option = (ConfigOption<?>) field.get(config);
                    option.setConfigIdentifier(configIdentifier);
                    options.add(option);
                }
                configOptionsMap.put(configClass, options);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ConfigFileWriter fileWriter = new ConfigFileWriter(config);
            configWriters.put(config.getClass(), fileWriter);
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

    public static List<ConfigOption<?>> getConfigOptions(Class<? extends ConfigProvider> clazz) {
        return configOptionsMap.getOrDefault(clazz, new ArrayList<>());
    }
}
