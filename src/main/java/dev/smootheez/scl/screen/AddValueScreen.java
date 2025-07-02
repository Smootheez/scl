package dev.smootheez.scl.screen;

import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class AddValueScreen extends Screen {
    private final Screen parent;

    protected AddValueScreen(Screen parent) {
        super(Component.translatable("config.screen.scl.addValue.title"));
        this.parent = parent;
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) this.minecraft.setScreen(parent);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, i, j, f);
    }
}
