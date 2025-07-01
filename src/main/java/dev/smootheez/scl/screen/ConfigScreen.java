package dev.smootheez.scl.screen;

import dev.smootheez.scl.gui.widget.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class ConfigScreen extends Screen {
    private final Screen parent;
    private ConfigListWidget listWidget;
    private final String configIdentifier;

    public ConfigScreen(Screen parent, String configIdentifier) {
        super(Component.literal("Example Screen")); //TODO: Change it into translatable `config.screen.[modid].title`
        this.parent = parent;
        this.configIdentifier = configIdentifier;
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();

        this.listWidget = new ConfigListWidget(this.minecraft, this.width, this.height, 32, this.height - 34, 24, configIdentifier);
        this.addRenderableWidget(listWidget);

        EditBox searchField = new EditBox(this.font, this.width / 2 + 5, 10, 100, 16, Component.translatable("widget.scl.search"));
        searchField.setMaxLength(50);
        //TODO: Implement search
        this.addRenderableWidget(searchField);

        this.addRenderableWidget(Button.builder(Component.translatable("widget.scl.save&exit"), btn -> {
            onClose();
            //TODO: Save config
        }).pos(this.width / 2 - 135, this.height - 25).size(130, 20).build()).active = false; //TODO: Enable when there is changes to save

        this.addRenderableWidget(Button.builder(Component.translatable("widget.scl.cancel"), btn ->
                onClose()).pos(this.width / 2 + 5, this.height - 25).size(130, 20).build());
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
