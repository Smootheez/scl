package dev.smootheez.scl.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class EnumSerializer<E extends Enum<E>> implements ConfigSerializer<E>{
    private final Class<E> clazz;

    public EnumSerializer(Class<E> clazz) {
        this.clazz = clazz;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Serializes the given enum by returning its name as a primitive JSON element.
     */
    @Override
    public JsonElement serialize(E value) {
        return new JsonPrimitive(value.name());
    }

    /**
     * {@inheritDoc}
     * <p>
     * Deserializes the given JSON element into an instance of the enum type.
     * The JSON element must represent the name of one of the enum constants.
     *
     * @param json the JSON element to deserialize
     * @return the enum constant represented by the JSON element
     * @throws IllegalArgumentException if the JSON element does not represent a valid enum constant
     */
    @Override
    public E deserialize(JsonElement json) {
        return Enum.valueOf(clazz, json.getAsString());
    }
}
