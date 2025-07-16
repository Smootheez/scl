package dev.smootheez.scl.gui.screen;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class OptionListScreen extends BaseConfigScreen{
    protected OptionListWidget widget;
    private final ConfigOption<OptionList> option;
    protected final String configIdentifier;
    private final Runnable onCloseCallback;

    public OptionListScreen(Screen parent, ConfigOption<OptionList> option, Runnable onCloseCallback) {
        super(Component.translatable("config.screen.scl.editValue.title"), parent);
        this.option = option;
        this.configIdentifier = option.getConfigIdentifier();
        this.onCloseCallback = onCloseCallback;
    }

    @Override
    protected void init() {
        this.widget = new OptionListWidget(this.minecraft, this.width, this.height, 32, this.height - 32, 24, this.option, this);
        this.addRenderableWidget(this.widget);

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.addValue"),
                        btn -> {
                            if (this.minecraft != null)
                                this.minecraft.setScreen(new AddValueScreen(this));
                        })
                .pos(this.width / 2 - 135, this.height - 25)
                .size(130, 20)
                .build());
        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.back"),
                btn -> onClose()).pos(this.width / 2 + 5, this.height - 25).size(130, 20).build());
    }

    @Override
    public void onClose() {
        super.onClose();
        if (onCloseCallback != null)
            onCloseCallback.run();
    }

    public void handleRemoveValueButton(String value) {
        OptionList optionList = this.option.getValue().copy();
        optionList.removeValue(value);
        updateWidget(optionList);
    }

    protected void handleAddValueButton(String value) {
        OptionList optionList = this.option.getValue().copy();
        optionList.addValue(value);
        updateWidget(optionList);
    }

    private void updateWidget(OptionList newValues) {
        this.option.setValue(newValues);
        this.widget.updateEntries();
    }

    @Override
    protected void handleSearchField(String search) {
        this.widget.search(search);
    }
}
