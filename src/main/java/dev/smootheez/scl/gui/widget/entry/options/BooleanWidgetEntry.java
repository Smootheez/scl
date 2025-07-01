package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class BooleanWidgetEntry extends LabeledWidgetEntry {
    private final CycleButton<Boolean> toggleButton;
    private final ConfigOption<Boolean> option;

    public BooleanWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<Boolean> option) {
        super(label, description);
        this.option = option;

        this.toggleButton = CycleButton.onOffBuilder(option.getValue())
                .displayOnlyValue()
                .create(0, 0, 80, 20, Component.literal(""),
                        (toggleButton, value) -> {
                            option.setValue(value);
                            updateResetButton();
                        });

        this.children.add(this.toggleButton);
        updateResetButton();
    }

    @Override
    public void resetButtonAction() {
        boolean defaultValue = option.getDefaultValue();
        option.setValue(defaultValue);
        toggleButton.setValue(defaultValue);
        updateResetButton();
    }

    @Override
    public void updateResetButton() {
        boolean currentValue = option.getValue();
        boolean defaultValue = option.getDefaultValue();
        getResetButton().active = currentValue != defaultValue;
    }
}
