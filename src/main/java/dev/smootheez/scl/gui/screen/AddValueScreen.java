package dev.smootheez.scl.gui.screen;

import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class AddValueScreen extends Screen {
    private final OptionListScreen parent;
    private EditBox addValueField;
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

        this.addValueField = new EditBox(this.font, widgetPositionX + 2, this.height / 2 - 30, widgetWidth - 4, widgetHeight, Component.translatable("config.widget.scl.addValue"));
        this.addValueField.setResponder(
                listener -> {
                    try {
                        addValueField.setTextColor(14737632);
                        this.value = listener;
                    } catch (Exception e) {
                        addValueField.setTextColor(16736352);
                    }
                }
        );
        this.addRenderableWidget(this.addValueField);

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

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics, i, j, f);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, this.height / 2 - 50, 0xFFFFFF);
        super.render(guiGraphics, i, j, f);
    }
}
