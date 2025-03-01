package dev.smootheez.scl.file;

import com.google.gson.JsonElement;
import dev.smootheez.scl.config.ConfigOption;

public record ConfigOptionAdapter<T>(ConfigOption<T> option) {
    void fromJson(JsonElement jsonElement) {
        option.setValue(option.getSerializer().deserialize(jsonElement));
    }

    JsonElement toJson() {
        return option.getSerializer().serialize(option.getValue());
    }
}
