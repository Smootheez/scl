package dev.smootheez.scl.config.serializer.options;

import com.google.gson.*;
import dev.smootheez.scl.config.serializer.*;

public class IntegerSerializer implements ConfigSerializer<Integer> {
    @Override
    public JsonElement serialize(Integer value) {
        return new JsonPrimitive(value);
    }

    @Override
    public Integer deserialize(JsonElement json) {
        return json.getAsInt();
    }
}
