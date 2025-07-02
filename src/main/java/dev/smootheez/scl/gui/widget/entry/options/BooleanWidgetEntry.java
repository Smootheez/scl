package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class BooleanWidgetEntry extends LabeledWidgetEntry<Boolean> {

    public BooleanWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<Boolean> option) {
        super(label, description, option);

        CycleButton<Boolean> toggleButton = CycleButton.onOffBuilder(option.getValue())
                .displayOnlyValue()
                .create(0, 0, 80, 20, Component.literal(""),
                        (button, value) -> {
                            option.setValue(value);
                            updateResetButton();
                        });

        this.children.add(toggleButton);
        updateResetButton();
    }
}
