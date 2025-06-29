package dev.smootheez.scl.config.serializer.options;

import com.google.gson.*;
import dev.smootheez.scl.config.serializer.*;

public class DoubleSerializer implements ConfigSerializer<Double> {
    @Override
    public JsonElement serialize(Double value) {
        return new JsonPrimitive(value);
    }

    @Override
    public Double deserialize(JsonElement json) {
        return json.getAsDouble();
    }
}
