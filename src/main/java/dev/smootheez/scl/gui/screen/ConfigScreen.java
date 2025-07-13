package dev.smootheez.scl.gui.screen;

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
    private boolean hasSnapshot = false;

    public ConfigScreen(Screen parent, String configIdentifier) {
        super(Component.translatable("config.screen." + configIdentifier + ".title"), parent);
        this.configIdentifier = configIdentifier;
    }

    @Override
    protected void init() {
        if (!this.hasSnapshot) {
            ConfigRegistry.createSnapshot(this.configIdentifier);
            this.hasSnapshot = true;
        }
        this.listWidget = new ConfigListWidget(this.minecraft, this.width, this.height - 64, 32, 24, configIdentifier);
        this.addRenderableWidget(this.listWidget);

        saveExitButton = this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.save&exit"), btn -> {
            if (this.minecraft != null) {
                ConfigRegistry.saveConfig(this.configIdentifier);
                this.minecraft.setScreen(parent);
            }
        }).pos(this.width / 2 - 135, this.height - 25).size(130, 20).build());

        this.addRenderableWidget(Button.builder(Component.translatable("config.widget.scl.back"),
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

        if (this.saveExitButton != null)
            this.saveExitButton.active = this.listWidget.hasChanged();
    }

    @Override
    public void onClose() {
        if (this.listWidget.hasChanged()) {
            if (this.minecraft != null) {
                Runnable saveAndExitAction = () -> {
                    ConfigRegistry.saveConfig(configIdentifier);
                    if (this.minecraft != null) {
                        this.minecraft.setScreen(this.parent);
                    }
                };

                Runnable discardAndExitAction = () -> {
                    ConfigRegistry.reloadConfig(configIdentifier);
                    if (this.minecraft != null) {
                        this.minecraft.setScreen(this.parent);
                    }
                };

                this.minecraft.setScreen(new ConfirmExitScreen(
                        this,
                        saveAndExitAction,
                        discardAndExitAction
                ));

            }
        } else {
            ConfigRegistry.discardSnapshot(configIdentifier);
            if (this.minecraft != null) {
                this.minecraft.setScreen(this.parent);
            }
        }
    }

    @Override
    protected void handleSearchField(String search) {
        this.listWidget.search(search);
    }
}
