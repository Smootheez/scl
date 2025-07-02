package dev.smootheez.scl.screen;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class ConfigScreen extends BaseConfigScreen {
    private final String configIdentifier;
    private ConfigListWidget listWidget;
    private Button saveExitButton;

    public ConfigScreen(Screen parent, String configIdentifier) {
        super(Component.translatable("config.screen." + configIdentifier + ".title"), parent);
        this.configIdentifier = configIdentifier;
    }

    @Override
    protected void init() {
        this.listWidget = new ConfigListWidget(this.minecraft, this.width, this.height, 32, this.height - 32, 24, configIdentifier);
        this.addRenderableWidget(this.listWidget);

        saveExitButton = this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.save&exit"), btn -> {
            if (this.minecraft != null) {
                this.minecraft.setScreen(parent);
                ConfigRegistry.saveConfig(this.configIdentifier);
            }
        }).pos(this.width / 2 - 135, this.height - 25).size(130, 20).build());

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.cancel"),
                btn -> onClose()).pos(this.width / 2 + 5, this.height - 25).size(130, 20).build());

        super.init();
    }

    @Override
    public boolean charTyped(char c, int keyCode) {
        if (searchField.isFocused() && searchField.charTyped(c, keyCode)) {
            return true;
        }
        return super.charTyped(c, keyCode);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (searchField.isFocused() && searchField.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double d, double e, int i) {
        if (this.searchField.mouseClicked(d, e, i)) {
            this.searchField.setFocused(true);
            for (ConfigWidgetEntry widgetEntry : listWidget.children()) {
                if (widgetEntry instanceof TextWidgetEntry<?> textWidgetEntry) {
                    textWidgetEntry.getEditBox().setFocused(false);
                }
            }
            return true;
        } else {
            this.searchField.setFocused(false);
        }
        return super.mouseClicked(d, e, i);
    }

    @Override
    public void tick() {
        super.tick();
        listWidget.tick();

        if (this.saveExitButton != null)
            this.saveExitButton.active = listWidget.hasChanged();
    }

    @Override
    public void onClose() {
        super.onClose();
        ConfigRegistry.reloadConfig(configIdentifier);
    }

    @Override
    protected void handleSearchField(String search) {
        this.listWidget.search(search);
    }
}
