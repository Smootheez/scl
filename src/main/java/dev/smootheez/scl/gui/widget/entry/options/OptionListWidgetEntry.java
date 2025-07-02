package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import dev.smootheez.scl.screen.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

public class OptionListWidgetEntry extends LabeledWidgetEntry<OptionList> {
    protected final Button button;

    public OptionListWidgetEntry(Component label, ConfigOption<OptionList> option) {
        super(label, null, option);

        button = Button.builder(Component.translatable("config.widget.scl.editValue"), b -> {
            var client = Minecraft.getInstance();
            var screen = client.screen;
            if (screen != null) client.setScreen(new OptionListScreen(screen, option));
            updateResetButton();
        }).size(80, 20).build();

        this.children.add(button);
        updateResetButton();
    }
}
