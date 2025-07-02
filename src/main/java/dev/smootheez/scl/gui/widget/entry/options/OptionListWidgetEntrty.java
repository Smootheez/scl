package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

public class OptionListWidgetEntrty extends LabeledWidgetEntry {
    private final Button button;
    private final ConfigOption<OptionList> option;

    public OptionListWidgetEntrty(Component label, ConfigOption<OptionList> option) {
        super(label, null);
        this.option = option;

        this.button = Button.builder(Component.translatable("widget.scl.editValue"), b -> {
            //TODO: handle navigate screen
            updateResetButton();
        }).size(80, 20).build();

        this.children.add(button);
        updateResetButton();
    }

    @Override
    public void resetButtonAction() {
        this.option.setValue(this.option.getDefaultValue());
        updateResetButton();
    }

    @Override
    public void updateResetButton() {
        this.resetButton.active = !this.option.getValue().equals(this.option.getDefaultValue());
    }
}
