package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import dev.smootheez.scl.screen.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

public class OptionListWidgetEntry extends LabeledWidgetEntry {
    private final ConfigOption<OptionList> option;

    public OptionListWidgetEntry(Component label, ConfigOption<OptionList> option) {
        super(label, null);
        this.option = option;

        Button button = Button.builder(Component.translatable("config.widget.scl.editValue"), b -> {
            var client = Minecraft.getInstance();
            var screen = client.screen;
            if (screen != null) client.setScreen(new OptionListScreen(screen, option));
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
