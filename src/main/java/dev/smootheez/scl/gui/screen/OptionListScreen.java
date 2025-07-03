package dev.smootheez.scl.gui.screen;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

import java.util.*;

public class OptionListScreen extends BaseConfigScreen{
    protected OptionListWidget widget;
    private final ConfigOption<OptionList> option;
    protected final String configIdentifier;
    private OptionList newValue;

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
                        btn -> handleAddValueButton())
                .pos(this.width / 2 - 135, this.height - 25)
                .size(130, 20)
                .build());
        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.back"),
                btn -> onClose()).pos(this.width / 2 + 5, this.height - 25).size(130, 20).build());

        super.init();
    }

    public OptionList getNewValue() {
        return newValue;
    }

    public void setNewValue(OptionList newValue) {
        this.newValue = newValue;
    }

    public void handleRemoveValueButton(String value, ValueListWidgetEntry entry) {
        OptionList optionList = this.option.getValue();
        List<String> values = new ArrayList<>(optionList.values());
        values.remove(value);
        this.widget.removeList(entry);
        var newValues = new OptionList(values);
        setNewValue(newValues);
        option.setValue(newValues);
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
