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

public class ConfigRegistry {
    private static final List<ConfigOption<?>> configOptions = new ArrayList<>();
    private static String modId;
    private static final Map<Class<? extends ConfigProvider>, ConfigFileWriter> configWriters = new HashMap<>();

    public static <T extends ConfigProvider> void registerConfig(T config){
        var configClass = config.getClass();
        if (configClass.getAnnotation(Config.class) != null) {
            modId = configClass.getAnnotation(Config.class).value();
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

    public static <T extends ConfigProvider> void save(Class<T> configClass) {
        ConfigFileWriter writer = configWriters.get(configClass);
        if (writer != null) writer.saveConfig();
        else throw new IllegalArgumentException("Config class not registered: " + configClass.getName());
    }

    private static List<Field> getConfigFields(Class<?> clazz) {
        List<Field> configFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().equals(ConfigOption.class)) configFields.add(field);
        }
        return configFields;
    }

    public static String getModId() {
        return modId;
    }

    public static List<ConfigOption<?>> getConfigOptions() {
        return configOptions;
    }
}
