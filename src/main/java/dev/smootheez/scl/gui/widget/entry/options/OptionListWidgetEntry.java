package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.screen.*;
import dev.smootheez.scl.gui.widget.clickable.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.network.chat.*;

public class OptionListWidgetEntry extends LabeledWidgetEntry<OptionList> {
    protected final ValueHoldingButton<OptionList> button;

    public OptionListWidgetEntry(Component label, ConfigOption<OptionList> option) {
        super(label, null, option);

        this.button = ValueHoldingButton.builder(Component.translatable("config.widget.scl.editValue"),
                b -> handleHoldingValueButton(), option.getValue()
        ).size(80, 20).build();

        this.children.add(button);
        updateResetButton();
    }

    private void handleHoldingValueButton() {
        var client = Minecraft.getInstance();
        var screen = client.screen;
        if (screen != null) {
            updateResetButton();
            client.setScreen(new OptionListScreen(screen, option, this::updateResetButton));
        }
    }

    @Override
    public void resetButtonAction() {
        super.resetButtonAction();
        button.setValue(this.option.getDefaultValue());
    }

    @Override
    public OptionList getValue() {
        return button.getValue();
    }
}
