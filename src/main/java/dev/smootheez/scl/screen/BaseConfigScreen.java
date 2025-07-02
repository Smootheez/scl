package dev.smootheez.scl.screen;

import dev.smootheez.scl.*;
import dev.smootheez.scl.config.*;
import dev.smootheez.scl.config.file.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class BaseConfigScreen extends Screen {
    protected final Screen parent;
    protected EditBox searchField;
    private String configIdentifier;

    public BaseConfigScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        initSearchField();

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.save&exit"), btn -> {
            onClose();
            if (this.configIdentifier != null)
                new ConfigFileWriter(this.configIdentifier).saveConfig();
            Constants.LOGGER.info("Saved config");
        }).pos(this.width / 2 - 135, this.height - 25).size(130, 20).build()); //TODO: Enable when there is changes to save

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.cancel"),
                btn -> onClose()).pos(this.width / 2 + 5, this.height - 25).size(130, 20).build());
    }

    protected void initSearchField() {
        this.searchField = new EditBox(this.font, this.width / 2 + 5, 10, 100, 16, Component.translatable("config.widget.scl.search"));
        this.searchField.setMaxLength(50);
        this.searchField.setResponder(this::searchFieldChanged);
        this.addRenderableWidget(this.searchField);
    }

    public void setConfigIdentifier(String configIdentifier) {
        this.configIdentifier = configIdentifier;
    }

    protected void searchFieldChanged(String search) {
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) this.minecraft.setScreen(parent);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, i, j, f);
        guiGraphics.drawString(this.font, this.title, this.width / 2 - this.font.width(this.title) - 10, 13, 0xFFFFFF);
    }
}
