package dev.smootheez.scl.gui.screen;

import dev.smootheez.scl.*;
import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

import java.util.*;
import java.util.function.*;

public class OptionListScreen extends BaseConfigScreen{
    protected OptionListWidget widget;
    private final ConfigOption<OptionList> option;
    protected final String configIdentifier;
    private OptionList currentValue;
    private final Consumer<OptionList> listConsumer;

    public OptionListScreen(Screen parent, ConfigOption<OptionList> option, OptionList currentValue, Consumer<OptionList> listConsumer) {
        super(Component.translatable("config.screen.scl.editValue.title"), parent);
        this.option = option;
        this.configIdentifier = option.getConfigIdentifier();
        this.currentValue = currentValue;
        this.listConsumer = listConsumer;
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

    public void handleRemoveValueButton(String value, ValueListWidgetEntry entry) {
        List<String> newValues = new ArrayList<>(this.option.getValue().values());
        newValues.remove(value);
        this.widget.removeList(entry);
        OptionList newValue = new OptionList(newValues);
        Constants.LOGGER.info("New Value: {}", newValue);
        this.currentValue = newValue;
        this.listConsumer.accept(newValue);
//        this.option.setValue(newValue);
    }

    @Override
    public void onClose() {
        super.onClose();
        this.listConsumer.accept(this.currentValue);
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
        this.widget.search(search);
    }
}
