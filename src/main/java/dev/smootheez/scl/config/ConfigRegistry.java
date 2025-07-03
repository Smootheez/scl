package dev.smootheez.scl.config;

import com.terraformersmc.modmenu.api.*;
import dev.smootheez.scl.*;
import dev.smootheez.scl.api.*;
import dev.smootheez.scl.config.file.*;
import dev.smootheez.scl.gui.screen.*;
import dev.smootheez.scl.util.*;

import java.lang.reflect.*;
import java.util.*;

public class ConfigRegistry {
    private static final Set<String> usedConfigIdentifiers = new HashSet<>();
    private static final Map<String, List<ConfigOption<?>>> configOptions = new HashMap<>();
    private static final Map<String, ConfigScreenFactory<?>> configScreenFactories = new HashMap<>();
    private static final Map<String, ConfigFileWriter> configWriters = new HashMap<>();

    public static void registerConfigs(Class<?> configClass) {
        Config annotation = configClass.getAnnotation(Config.class);
        Constants.LOGGER.info("Registering configs");

        String configIdentifier = annotation.name();
        if (usedConfigIdentifiers.contains(configIdentifier))
            throw new IllegalArgumentException("Config identifier " + configIdentifier + " is already used");
        Constants.LOGGER.info("Registered config {}", configIdentifier);
        usedConfigIdentifiers.add(configIdentifier);

        try {
            List<Field> configFields = getConfigFields(configClass);
            List<ConfigOption<?>> options = new ArrayList<>();
            for (Field field : configFields) {
                field.setAccessible(true);
                ConfigOption<?> option = (ConfigOption<?>) field.get(configClass);
                option.setConfigIdentifier(configIdentifier);
                options.add(option);
            }
            configOptions.put(configIdentifier, options);
            ConfigFileWriter configFileWriter = new ConfigFileWriter(configIdentifier);
            configWriters.put(configIdentifier, configFileWriter);
            configFileWriter.loadConfig();

            if (ModChecker.isModInstalled(configIdentifier) && ModChecker.isModInstalled("modmenu") && annotation.gui())
                configScreenFactories.put(configIdentifier, screen -> new ConfigScreen(screen, configIdentifier));
            else Constants.LOGGER.info("Skipping gui registration");
        } catch (IllegalAccessException e) {
            Constants.LOGGER.error("Failed to register configs", e);
        }
    }

    public static void saveConfig(String configIdentifier) {
        ConfigFileWriter writer = configWriters.get(configIdentifier);
        if (writer!= null) writer.saveConfig();
        Constants.LOGGER.info("Saved config for {}", configIdentifier);
    }

    public static void reloadConfig(String configIdentifier) {
        ConfigFileWriter writer = configWriters.get(configIdentifier);
        if (writer != null) writer.loadConfig();
        Constants.LOGGER.info("Reloaded config for {}", configIdentifier);
    }

    public static Map<String, ConfigScreenFactory<?>> getConfigScreenFactories() {
        return configScreenFactories;
    }

    public static List<ConfigOption<?>> getConfigOptions(String configIdentifier) {
        return configOptions.getOrDefault(configIdentifier, new ArrayList<>());
    }

    private static List<Field> getConfigFields(Class<?> configClass) {
        List<Field> configFields = new ArrayList<>();
        for (Field field : configClass.getDeclaredFields())
            if (field.getType().equals(ConfigOption.class)) configFields.add(field);
        return configFields;
    }
}
