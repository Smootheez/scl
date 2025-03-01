package dev.smootheez.scl.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class DoubleSerializer implements ConfigSerializer<Double> {
    /**
     * Serializes a double value into a json primitive element.
     *
     * @param value the double value to be serialized
     * @return a json primitive element containing the serialized value
     */
    @Override
    public JsonElement serialize(Double value) {
        return new JsonPrimitive(value);
    }

    /**
     * Deserializes a double value from a json primitive element.
     *
     * @param json the json primitive element containing the value to be deserialized
     * @return the deserialized double value
     */
    @Override
    public Double deserialize(JsonElement json) {
        return json.getAsDouble();
    }
}
