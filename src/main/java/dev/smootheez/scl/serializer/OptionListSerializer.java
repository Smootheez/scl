package dev.smootheez.scl.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import dev.smootheez.scl.config.option.OptionList;

import java.util.ArrayList;
import java.util.List;

public class OptionListSerializer implements ConfigSerializer<OptionList> {
    /**
     * Serializes the given ConfigOptionList into a JsonElement. The serialized form
     * is a JsonArray where each element is a JsonPrimitive containing the value
     * of each option in the list.
     *
     * @param value the ConfigOptionList to serialize
     * @return a JsonElement containing the serialized ConfigOptionList
     */
    @Override
    public JsonElement serialize(OptionList value) {
        JsonArray array = new JsonArray();
        for (String s : value.values()) {
            array.add(new JsonPrimitive(s));
        }
        return array;
    }

    /**
     * Deserializes the given JsonElement into a ConfigOptionList. The expected
     * serialized form is a JsonArray where each element is a JsonPrimitive
     * containing the value of each option in the list.
     *
     * @param json the JsonElement to deserialize
     * @return a ConfigOptionList containing the deserialized values
     */
    @Override
    public OptionList deserialize(JsonElement json) {
        JsonArray array = json.getAsJsonArray();
        List<String> list = new ArrayList<>();
        for (JsonElement element : array) {
            list.add(element.getAsString());
        }
        return new OptionList(list);
    }
}
