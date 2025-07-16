package dev.smootheez.scl.gui.screen;

import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public abstract class BaseConfigScreen extends Screen {
    protected final Screen parent;
    protected EditBox searchField;

    public BaseConfigScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        StringWidget titleWidget = new StringWidget(this.title, this.font);
        titleWidget.setX(this.width / 2 - titleWidget.getWidth() - 10);
        titleWidget.setY(10);
        this.addRenderableWidget(titleWidget);

        this.searchField = new EditBox(this.font, this.width / 2 + 5, 10, 100, 16, Component.translatable("config.widget.scl.search"));
        this.searchField.setMaxLength(50);
        this.searchField.setResponder(this::handleSearchField);
        this.addRenderableWidget(this.searchField);
        this.setFocused(this.searchField);
    }

    protected abstract void handleSearchField(String search);

    @Override
    public void onClose() {
        if (this.minecraft != null) this.minecraft.setScreen(parent);
    }

    @Override
    public void tick() {
        super.tick();
        this.searchField.tick();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, i, j, f);
    }
}
