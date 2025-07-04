package dev.smootheez.scl.config;

import java.util.*;

public record OptionList(List<String> values) {
    public OptionList(List<String> values) {
        this.values = new ArrayList<>(values);
    }

    public OptionList copy() {
        return new OptionList(this.values);
    }

    public List<String> values() {
        return new ArrayList<>(values);
    }

    public void addValue(String value) {
        values.add(value);
    }

    public void removeValue(String value) {
        values.remove(value);
    }

    public String getValue(int index) {
        if (index >= 0 && index < values.size()) return values.get(index);
        return new IndexOutOfBoundsException("Index: " + index + ", Size: " + values.size()).toString();
    }
}
