package dev.smootheez.scl.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class BooleanSerializer implements ConfigSerializer<Boolean> {
    /**
     * {@inheritDoc}
     * <p>
     * Serializes the given boolean value as a {@link JsonPrimitive}.
     * @param value the boolean value to serialize
     * @return the serialized boolean value
     */
    @Override
    public JsonElement serialize(Boolean value) {
        return new JsonPrimitive(value);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Deserializes the given json element as a boolean value.
     * @param json the json element to deserialize
     * @return the deserialized boolean value
     */
    @Override
    public Boolean deserialize(JsonElement json) {
        return json.getAsBoolean();
    }
}
