package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class CycleWidgetEntry<T extends Enum<T>> extends LabeledWidgetEntry<T> {
    private final CycleButton<T> cycleButton;

    public CycleWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<T> option) {
        super(label, description, option);
        T[] enumValues = option.getType().getEnumConstants();

        this.cycleButton = CycleButton.<T>builder(e -> Component.translatable(option.getTranslation() + "." + toCammelCase(e.name())))
                .displayOnlyValue()
                .withValues(enumValues)
                .withInitialValue(option.getValue())
                .create(0, 0, 80, 20, Component.literal(""),
                        (toggleButton, value) -> {
                    option.setValue(value);
                    ConfigRegistry.markConfigAsDirty();
                    updateResetButton();
                });

        this.children.add(this.cycleButton);
        updateResetButton();
    }

    @Override
    public void resetButtonAction() {
        T defaultValue = option.getDefaultValue();
        option.setValue(defaultValue);
        cycleButton.setValue(defaultValue);
        ConfigRegistry.markConfigAsDirty();
        updateResetButton();
    }

    private String toCammelCase(String name) {
        StringBuilder camelCaseName = new StringBuilder();
        String[] parts = name.toLowerCase().split("_");
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (!part.isEmpty()) {
                if (i == 0) {
                    camelCaseName.append(part);
                } else {
                    camelCaseName.append(part.substring(0, 1).toUpperCase())
                            .append(part.substring(1));
                }
            }
        }
        return camelCaseName.toString();
    }
}
