package dev.smootheez.scl.serializer;

import com.google.gson.JsonElement;

public interface ConfigSerializer<T> {
    JsonElement serialize(T value);
    T deserialize(JsonElement json);
}
