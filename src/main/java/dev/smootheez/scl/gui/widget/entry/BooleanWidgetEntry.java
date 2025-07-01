package dev.smootheez.scl.gui.widget.entry;

import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

import java.util.concurrent.atomic.*;

public class BooleanWidgetEntry extends LabeledWidgetEntry {
    private final CycleButton<Boolean> toggleButton;

    public BooleanWidgetEntry() {
        super(Component.literal("Test Toggle Button"), null);
        AtomicBoolean value = new AtomicBoolean(true);

        this.toggleButton = CycleButton.onOffBuilder(value.get())
                .displayOnlyValue()
                .create(0, 0, 80, 20, Component.literal("Test Toggle Button"), (toggleButton, boolean_) -> value.set(false));

        this.children.add(this.toggleButton);
        getResetButton().active = false;
    }
}
