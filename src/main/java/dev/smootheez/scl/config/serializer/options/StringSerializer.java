package dev.smootheez.scl.config.serializer.options;

import com.google.gson.*;
import dev.smootheez.scl.config.serializer.*;

public class StringSerializer implements ConfigSerializer<String> {
    @Override
    public JsonElement serialize(String value) {
        return new JsonPrimitive(value);
    }

    @Override
    public String deserialize(JsonElement json) {
        return json.getAsString();
    }
}
