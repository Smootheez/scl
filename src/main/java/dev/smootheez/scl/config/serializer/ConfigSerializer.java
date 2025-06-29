package dev.smootheez.scl.config.serializer;

import com.google.gson.*;

public interface ConfigSerializer<T> {
    JsonElement serialize(T value);
    T deserialize(JsonElement json);
}
