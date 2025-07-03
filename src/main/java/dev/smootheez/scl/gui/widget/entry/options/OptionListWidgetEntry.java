package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import dev.smootheez.scl.gui.screen.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

public class OptionListWidgetEntry extends LabeledWidgetEntry<OptionList> {
    protected final Button button;
    private final OptionListScreen screen;

    public OptionListWidgetEntry(Component label, ConfigOption<OptionList> option) {
        super(label, null, option);
        var client = Minecraft.getInstance();
        var screen = client.screen;
        this.screen = new OptionListScreen(screen, option);

        button = Button.builder(Component.translatable("config.widget.scl.editValue"), b -> {
            if (screen != null) client.setScreen(this.screen);
            updateResetButton();
        }).size(80, 20).build();

        this.children.add(button);
        updateResetButton();
    }

    @Override
    public OptionList getValue() {
        return option.getValue();
    }
}
