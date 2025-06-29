package dev.smootheez.scl.config.serializer.options;

import com.google.gson.*;
import dev.smootheez.scl.config.*;
import dev.smootheez.scl.config.serializer.*;

import java.util.*;

public class OptionListSerializer implements ConfigSerializer<OptionList> {
    @Override
    public JsonElement serialize(OptionList value) {
        JsonArray array = new JsonArray();
        for (String s : value.values()) {
            array.add(new JsonPrimitive(s));
        }
        return array;
    }

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
