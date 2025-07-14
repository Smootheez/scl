package dev.smootheez.scl.gui.screen;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.layouts.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class OptionListScreen extends BaseConfigScreen{
    protected OptionListWidget listWidget;
    private final ConfigOption<OptionList> option;
    protected final String configIdentifier;

    public OptionListScreen(Screen parent, ConfigOption<OptionList> option) {
        super(Component.translatable("config.screen.scl.editValue.title"), parent);
        this.option = option;
        this.configIdentifier = option.getConfigIdentifier();
    }

    @Override
    protected void init() {
        this.listWidget = this.layout.addToContents(new OptionListWidget(this.minecraft, this.width, this.height - 64, 32, 24, this.option, this));

        LinearLayout footerLinearLayout = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
        footerLinearLayout.addChild(Button.builder(Component.translatable("config.widget.scl.addValue"),
                        btn -> {
                            if (this.minecraft != null)
                                this.minecraft.setScreen(new AddValueScreen(this));
                        })
                .size(130, 20)
                .build());
        footerLinearLayout.addChild(Button.builder(Component.translatable("config.widget.scl.back"),
                btn -> onClose()).size(130, 20).build());

        LinearLayout headerLinearLayout = this.layout.addToHeader(LinearLayout.horizontal().spacing(8));

        headerLinearLayout.addChild(new StringWidget(this.title, this.font));

        this.searchField = headerLinearLayout.addChild(new EditBox(this.font,100, 20, Component.translatable("config.widget.scl.search")));
        this.searchField.setMaxLength(50);
        this.searchField.setResponder(this::handleSearchField);
        this.setFocused(this.searchField);

        this.layout.visitWidgets(this::addRenderableWidget);
        this.repositionElements();
    }

    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
        if (this.listWidget != null)
            this.listWidget.updateSize(this.width, this.layout);
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
        this.listWidget.updateEntries();
    }

    @Override
    protected void handleSearchField(String search) {
        this.listWidget.search(search);
    }
}
