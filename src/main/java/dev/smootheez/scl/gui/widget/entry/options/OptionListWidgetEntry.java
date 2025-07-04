package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.*;
import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.screen.*;
import dev.smootheez.scl.gui.widget.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.network.chat.*;

import java.util.function.*;

public class OptionListWidgetEntry extends LabeledWidgetEntry<OptionList> {
    protected final ValueHoldingButton<OptionList> button;

    public OptionListWidgetEntry(Component label, ConfigOption<OptionList> option) {
        super(label, null, option);

        for (String s : option.getValue().values())
            Constants.LOGGER.info("Option List Widget Value: {}", s);

        this.button = ValueHoldingButton.builder(Component.translatable("config.widget.scl.editValue"),
                b -> handleHoldingValueButton(), option.getValue().copy()
        ).size(80, 20).build();

        Constants.LOGGER.info("Button Value: {}", button.getValue());

        this.children.add(button);
        updateResetButton();
    }

    private void handleHoldingValueButton() {
        var client = Minecraft.getInstance();
        var screen = client.screen;
        if (screen != null) {
            Consumer<OptionList> valueChangeCallback = (newValue) -> {
                this.button.setValue(newValue.copy());
                this.option.setValue(newValue);
                updateResetButton();
            };
            client.setScreen(new OptionListScreen(screen, option, button.getValue(), valueChangeCallback));
        }
    }

    @Override
    public void resetButtonAction() {
        super.resetButtonAction();
        button.setValue(this.option.getDefaultValue().copy());
    }

    @Override
    public OptionList getValue() {
        return button.getValue();
    }
}
