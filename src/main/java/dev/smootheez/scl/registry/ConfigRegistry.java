package dev.smootheez.scl.registry;

import dev.smootheez.scl.annotation.Config;
import dev.smootheez.scl.api.ConfigProvider;
import dev.smootheez.scl.config.ConfigOption;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ConfigRegistry {
    private static final List<ConfigOption<?>> configOptions = new ArrayList<>();
    private static String modId;

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
        }
    }

    private static List<Field> getConfigFields(Class<?> clazz) {
        List<Field> configFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().equals(ConfigOption.class)) configFields.add(field);
        }
        return configFields;
    }

    public static List<ConfigOption<?>> getConfigOptions() {
        return configOptions;
    }

    public static String getModId() {
        return modId;
    }
}
