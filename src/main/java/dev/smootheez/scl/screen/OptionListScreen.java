package dev.smootheez.scl.screen;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class OptionListScreen extends BaseConfigScreen{
    protected OptionListWidget widget;
    private final ConfigOption<OptionList> option;
    protected final String configIdentifier;

    public OptionListScreen(Screen parent, ConfigOption<OptionList> option) {
        super(Component.translatable("config.screen.scl.editValue.title"), parent);
        this.option = option;
        this.configIdentifier = option.getConfigIdentifier();
    }

    @Override
    protected void init() {
        this.widget = new OptionListWidget(this.minecraft, this.width, this.height, 32, this.height - 32, 24, this.option);
        this.addRenderableWidget(this.widget);

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.addValue"),
                        btn -> handleAddValueButton())
                .pos(this.width / 2 - 135, this.height - 25)
                .size(130, 20)
                .build());
        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.back"),
                btn -> onClose()).pos(this.width / 2 + 5, this.height - 25).size(130, 20).build());

        super.init();
    }

    protected void handleAddValueButton() {
        if (this.minecraft != null)
            this.minecraft.setScreen(new AddValueScreen(this));
    }

    public void refreshListWidget() {
        widget.clearList();
    }

    @Override
    protected void handleSearchField(String search) {
        super.handleSearchField(search); //TODO: implement search for option list
    }
}
