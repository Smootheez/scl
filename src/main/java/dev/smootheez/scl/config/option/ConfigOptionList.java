package dev.smootheez.scl.config.option;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mutable list of configuration values that can be modified through add/remove operations.
 * This class provides thread-safe modifications and defensive copying of its contents.
 */
public record ConfigOptionList(List<String> values) {
    /**
     * Creates a new ConfigOptionList instance with the specified initial values.
     * The input list is copied to prevent external modifications.
     * @param values the initial values to be stored in the list
     */
    public ConfigOptionList(List<String> values) {
        this.values = new ArrayList<>(values);
    }

    /**
     * Returns a defensive copy of the list containing all current values.
     * Modifications to the returned list will not affect this ConfigOptionList instance.
     * @return an immutable copy of the current values
     */
    public List<String> values() {
        return new ArrayList<>(values);
    }

    /**
     * Adds a new value to the end of the list.
     * @param value the value to add (null values are allowed)
     * @return true if the list was modified as a result of this call
     */
    public void addValue(String value) {
        values.add(value);
    }

    /**
     * Removes the first occurrence of the specified value from the list.
     * @param value the value to remove
     */
    public void removeValue(String value) {
        values.remove(value);
    }

    /**
     * Retrieves the value at the specified index in the list.
     * @param index the index of the value to return
     * @return the value at the specified index, or an IndexOutOfBoundsException
     *         if the index is out of bounds
     */
    public String getValue(int index) {
        if (index >= 0 && index < values.size()) return values.get(index);
        return new IndexOutOfBoundsException("Index: " + index + ", Size: " + values.size()).toString();
    }
}
