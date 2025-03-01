package dev.smootheez.scl.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class IntegerSerializer implements ConfigSerializer<Integer> {
    /**
     * {@inheritDoc}
     *
     * @param value the integer to be serialized
     * @return a new {@link JsonPrimitive} with the given value
     */
    @Override
    public JsonElement serialize(Integer value) {
        return new JsonPrimitive(value);
    }

    /**
     * {@inheritDoc}
     *
     * @param json the {@link JsonElement} to be deserialized
     * @return the integer value extracted from the given JSON element
     */
    @Override
    public Integer deserialize(JsonElement json) {
        return json.getAsInt();
    }
}
