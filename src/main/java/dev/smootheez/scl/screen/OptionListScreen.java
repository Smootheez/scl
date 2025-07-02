package dev.smootheez.scl.screen;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
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
        this.setConfigIdentifier(this.configIdentifier);
    }

    @Override
    protected void init() {
        this.widget = new OptionListWidget(this.minecraft, this.width, this.height, 32, this.height - 32, 24, this.option);
        this.addRenderableWidget(this.widget);
        initAddValueButton();
        initBackButton();
        super.init();
    }

    @Override
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
