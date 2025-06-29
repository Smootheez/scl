package dev.smootheez.scl.screen;

import dev.smootheez.scl.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class ConfigScreen extends Screen {
    private final Screen screen;

    public ConfigScreen(Screen screen, String configIdentifier) {
        super(Component.translatable("config.screen." + configIdentifier + ".title"));
        this.screen = screen;
    }

    @Override
    protected void init() {
        addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> onClose())
                .width(220)
                .pos(this.width / 2 - 220 / 2, this.height - 27)
                .build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) this.minecraft.setScreen(screen);
    }
}
