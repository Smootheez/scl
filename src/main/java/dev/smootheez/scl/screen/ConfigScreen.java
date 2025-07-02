package dev.smootheez.scl.screen;

import dev.smootheez.scl.gui.widget.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public class ConfigScreen extends BaseConfigScreen {
    private final String configIdentifier;
    private ConfigListWidget listWidget;

    public ConfigScreen(Screen parent, String configIdentifier) {
        super(Component.translatable("config.screen." + configIdentifier + ".title"), parent);
        this.configIdentifier = configIdentifier;
    }

    @Override
    protected void init() {
        this.listWidget = new ConfigListWidget(this.minecraft, this.width, this.height, 32, this.height - 32, 24, configIdentifier);
        this.addRenderableWidget(this.listWidget);

        super.init();
    }

    @Override
    protected void searchFieldChanged(String search) {
        this.listWidget.search(search);
    }
}
