package dev.smootheez.scl.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.registry.ConfigRegistry;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ConfigFileWriter {
    private final Gson gson;
    private final File configFile;
    private final Map<String, ConfigOptionAdapter<?>> adapterMap;

    public ConfigFileWriter() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.adapterMap = new TreeMap<>();

        this.configFile = FabricLoader.getInstance().getConfigDir().resolve(ConfigRegistry.getModId() + ".json").toFile();

        Set<String> usedKeys = new HashSet<>();
        List<ConfigOption<?>> configOptions = ConfigRegistry.getConfigOptions();
        for (ConfigOption<?> option : configOptions) {
            String key = option.getKey();
            if (usedKeys.contains(key)) throw new IllegalStateException("Duplicate key found: " + key);
            usedKeys.add(key);
            adapterMap.put(key, createAdapter(option));
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
            throw new RuntimeException("Error load config ", e);
        }
    }

    public void saveConfig() {
        try (var writer = new FileWriter(configFile)){
            JsonObject object = toJson();
            gson.toJson(object, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error saving config ", e);
        }
    }

    private void fromJson(JsonObject jsonObject) {
        adapterMap.forEach((key, adapter) -> {
            if (jsonObject.has(key)) adapter.fromJson(jsonObject.get(key));
        });
    }

    private JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        adapterMap.forEach((key, adapter) -> jsonObject.add(key, adapter.toJson()));
        return jsonObject;
    }

    private <T> ConfigOptionAdapter<T> createAdapter(ConfigOption<T> option) {
        return new ConfigOptionAdapter<>(option);
    }
}
