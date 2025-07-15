package dev.smootheez.scl.gui.screen;

import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class ConfirmExitScreen extends Screen {
    private final ConfigScreen parent;
    private final Runnable onCorfirm;
    private final Runnable onCancel;

    protected ConfirmExitScreen(ConfigScreen parent, Runnable onCorfirm, Runnable onCancel) {
        super(Component.translatable("config.screen.scl.confirmExit.title"));
        this.parent = parent;
        this.onCorfirm = onCorfirm;
        this.onCancel = onCancel;
    }

    @Override
    protected void init() {
        var widgetWidth = 150;
        var widgetHeight = 20;
        var widgetPositionX = this.width / 2;

        StringWidget stringWidget = new StringWidget(this.title, this.font);
        stringWidget.setX(widgetPositionX - stringWidget.getWidth() / 2);
        stringWidget.setY(this.height / 2 - 20);
        this.addRenderableWidget(stringWidget);

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.save&exit"),
                btn -> this.onCorfirm.run()).pos(widgetPositionX - widgetWidth - 5, this.height / 2 + 5).size(widgetWidth, widgetHeight).build());

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.discard&exit"),
                btn -> this.onCancel.run()).pos(widgetPositionX + 5, this.height / 2 + 5).size(widgetWidth, widgetHeight).build());
    }

    @Override
    public void onClose() {
        if (this.minecraft!= null) this.minecraft.setScreen(parent);
    }
}
