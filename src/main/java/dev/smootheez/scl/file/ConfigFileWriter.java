package dev.smootheez.scl.file;

import dev.smootheez.scl.config.ConfigOption;

public class ConfigFileWriter {
    private <T> ConfigOptionAdapter<T> createAdapter(ConfigOption<T> option) {
        return new ConfigOptionAdapter<>(option);
    }
}
