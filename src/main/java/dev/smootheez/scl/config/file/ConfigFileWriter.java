package dev.smootheez.scl.config.file;

import com.google.gson.*;
import dev.smootheez.scl.*;
import dev.smootheez.scl.config.*;
import net.fabricmc.loader.api.*;

import java.io.*;
import java.util.*;

public class ConfigFileWriter {
    private final Gson gson;
    private final File configFile;
    private final Map<String, Map<String, ConfigOptionAdapter<?>>> categorizedOptions = new TreeMap<>();
    private final Map<String, ConfigOptionAdapter<?>> rootOptions = new TreeMap<>();


    public ConfigFileWriter(String configIdentifier) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.configFile = FabricLoader.getInstance().getConfigDir().resolve(configIdentifier + ".json").toFile();

        Set<String> usedKeys = new HashSet<>();
        List<ConfigOption<?>> configOptions = ConfigRegistry.getConfigOptions(configIdentifier);
        for (ConfigOption<?> option : configOptions) {
            String key = option.getKey();
            String category = option.getCategory();

            if (usedKeys.contains(key)) {
                Constants.LOGGER.warn("Config key {} is already used", key);
                continue;
            }

            if (key.equals(category))
                throw new IllegalArgumentException("Config key '" + key + "' cannot be the same as its category.");

            usedKeys.add(key);
            ConfigOptionAdapter<?> adapter = createAdapter(option);

            if (category == null) {
                rootOptions.put(key, adapter);
            } else {
                categorizedOptions
                        .computeIfAbsent(category, k -> new TreeMap<>())
                        .put(key, adapter);
            }
        }

    }

    public void loadConfig() {
        if (!configFile.exists()) {
            saveConfig();
            return;
        }

        try (var reader = new FileReader(configFile)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            fromJson(jsonObject);
        } catch (IOException e) {
            Constants.LOGGER.error("Failed to load config", e);
        }
    }

    public void saveConfig() {
        try (var writer = new FileWriter(configFile)) {
            JsonObject jsonObject = toJson();
            gson.toJson(jsonObject, writer);
        } catch (IOException e) {
            Constants.LOGGER.error("Failed to save config", e);
        }
    }

    private void fromJson(JsonObject jsonObject) {
        rootOptions.forEach((key, adapter) -> {
            if (jsonObject.has(key)) adapter.fromJson(jsonObject.get(key));
        });

        categorizedOptions.forEach((category, map) -> {
            if (!jsonObject.has(category)) return;
            JsonObject categoryJson = jsonObject.getAsJsonObject(category);
            map.forEach((key, adapter) -> {
                if (categoryJson.has(key)) adapter.fromJson(categoryJson.get(key));
            });
        });
    }


    private JsonObject toJson() {
        JsonObject root = new JsonObject();

        rootOptions.forEach((key, adapter) -> root.add(key, adapter.toJson()));

        for (Map.Entry<String, Map<String, ConfigOptionAdapter<?>>> entry : categorizedOptions.entrySet()) {
            JsonObject categoryObject = new JsonObject();
            entry.getValue().forEach((key, adapter) -> categoryObject.add(key, adapter.toJson()));
            root.add(entry.getKey(), categoryObject);
        }

        return root;
    }


    private <T> ConfigOptionAdapter<T> createAdapter(ConfigOption<T> option) {
        return new ConfigOptionAdapter<>(option);
    }
}
