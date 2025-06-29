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
    private final Map<String, ConfigOptionAdapter<?>> options;

    public ConfigFileWriter(String configIdentifier) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.options = new TreeMap<>();
        this.configFile = FabricLoader.getInstance().getConfigDir().resolve(configIdentifier + ".json").toFile();

        Set<String> usedKeys = new HashSet<>();
        List<ConfigOption<?>> configOptions = ConfigRegistry.getConfigOptions(configIdentifier);
        for (ConfigOption<?> option : configOptions) {
            String key = option.getKey();
            if (usedKeys.contains(key))
                Constants.LOGGER.warn("Config key {} is already used", key);
            usedKeys.add(key);
            options.put(key, createAdapter(option));
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
        options.forEach((key, adapter) -> {
            if (jsonObject.has(key)) adapter.fromJson(jsonObject.get(key));
        });
    }

    private JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        options.forEach((key, adapter) -> jsonObject.add(key, adapter.toJson()));
        return jsonObject;
    }

    private <T> ConfigOptionAdapter<T> createAdapter(ConfigOption<T> option) {
        return new ConfigOptionAdapter<>(option);
    }
}
