package dev.smootheez.scl.config.serializer.options;

import com.google.gson.*;
import dev.smootheez.scl.config.serializer.*;

public class BooleanSerializer implements ConfigSerializer<Boolean> {
    @Override
    public JsonElement serialize(Boolean value) {
        return new JsonPrimitive(value);
    }

    @Override
    public Boolean deserialize(JsonElement json) {
        return json.getAsBoolean();
    }
}
