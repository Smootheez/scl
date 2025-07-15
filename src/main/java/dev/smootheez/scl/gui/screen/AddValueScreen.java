package dev.smootheez.scl.gui.screen;

import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class AddValueScreen extends Screen {
    private final OptionListScreen parent;
    private String value;

    protected AddValueScreen(OptionListScreen parent) {
        super(Component.translatable("config.screen.scl.addValue.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        var widgetWidth = 200;
        var widgetHeight = 20;
        var widgetPositionX = this.width / 2 - widgetWidth / 2;

        StringWidget stringWidget = new StringWidget(this.title, this.font);
        stringWidget.setX(this.width / 2 - stringWidget.getWidth() / 2);
        stringWidget.setY(this.height / 2 - 50);
        this.addRenderableWidget(stringWidget);

        EditBox addValueField = new EditBox(this.font, widgetPositionX, this.height / 2 - 35, widgetWidth - 4, widgetHeight, Component.translatable("config.widget.scl.addValue"));
        addValueField.setResponder(listener -> this.value = listener);
        this.setFocused(addValueField);
        this.addRenderableWidget(addValueField);

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.addValue"),
                        b -> {
            onClose();
            parent.handleAddValueButton(this.value);
        }).pos(widgetPositionX, this.height / 2).size(widgetWidth, widgetHeight).build());

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.cancel"),
                        b -> onClose()).pos(widgetPositionX, this.height / 2 + 25).size(widgetWidth, widgetHeight).build());
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) this.minecraft.setScreen(parent);
    }
}
