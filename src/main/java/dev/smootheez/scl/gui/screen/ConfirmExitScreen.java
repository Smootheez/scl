package dev.smootheez.scl.gui.screen;

import net.minecraft.client.gui.*;
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
        super.init();

        var widgetWidth = 150;
        var widgetHeight = 20;
        var widgetPositionX = this.width / 2;
        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.save&exit"),
                btn -> this.onCorfirm.run()).pos(widgetPositionX - widgetWidth - 5, this.height / 2 + 5).size(widgetWidth, widgetHeight).build());

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.discard&exit"),
                btn -> this.onCancel.run()).pos(widgetPositionX + 5, this.height / 2 + 5).size(widgetWidth, widgetHeight).build());
    }

    @Override
    public void onClose() {
        if (this.minecraft!= null) this.minecraft.setScreen(parent);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, this.height / 2 - 20, 0xFFFFFF);
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderDirtBackground(guiGraphics);
    }
}
