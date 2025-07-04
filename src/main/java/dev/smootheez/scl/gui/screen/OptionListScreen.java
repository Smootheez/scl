package dev.smootheez.scl.gui.screen;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

import java.util.*;

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
        this.widget = new OptionListWidget(this.minecraft, this.width, this.height, 32, this.height - 32, 24, this.option, this);
        this.addRenderableWidget(this.widget);

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.addValue"),
                        btn -> {
                            if (this.minecraft == null)
                                return;
                            this.minecraft.setScreen(new AddValueScreen(this));
                            handleAddValueButton();
                        })
                .pos(this.width / 2 - 135, this.height - 25)
                .size(130, 20)
                .build());
        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.back"),
                btn -> onClose()).pos(this.width / 2 + 5, this.height - 25).size(130, 20).build());

        super.init();
    }

    public void handleRemoveValueButton(String value) {
        List<String> newValues = this.option.getValue().values();
        newValues.remove(value);
        updateWidget(newValues);
    }

    protected void handleAddValueButton(String value) {
        List<String> newValues = this.option.getValue().values();
        newValues.add(value);
        updateWidget(newValues);
    }

    private void updateWidget(List<String> newValues) {
        this.option.setValue(new OptionList(newValues));
        this.widget.updateEntries();
    }

    @Override
    protected void handleSearchField(String search) {
        this.widget.search(search);
    }
}
