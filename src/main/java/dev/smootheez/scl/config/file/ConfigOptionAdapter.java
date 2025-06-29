package dev.smootheez.scl.config.file;

import com.google.gson.*;
import dev.smootheez.scl.config.*;

public record ConfigOptionAdapter<T>(ConfigOption<T> option) {
    void fromJson(JsonElement element) {
        option.setValue(option.getSerializer().deserialize(element));
    }

    JsonElement toJson() {
        return option.getSerializer().serialize(option.getValue());
    }
}
