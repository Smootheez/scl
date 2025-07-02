package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class BooleanWidgetEntry extends LabeledWidgetEntry<Boolean> {
    private final CycleButton<Boolean> toggleButton;

    public BooleanWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<Boolean> option) {
        super(label, description, option);
        toggleButton = CycleButton.onOffBuilder(option.getValue())
                .displayOnlyValue()
                .create(0, 0, 80, 20, Component.literal(""),
                        (button, value) -> {
                            option.setValue(value);
                            updateResetButton();
                            ConfigRegistry.markConfigAsDirty();
                        });

        this.children.add(toggleButton);
        updateResetButton();
    }

    @Override
    public void resetButtonAction() {
        toggleButton.setValue(option.getDefaultValue());
        super.resetButtonAction();
    }
}
