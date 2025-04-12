package dev.smootheez.scl.screen;

import dev.smootheez.scl.Constants;
import dev.smootheez.scl.widget.ConfigListWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ConfigScreen extends Screen {
    private final Screen screen;
    private ConfigListWidget configListWidget;
    private final String configIdentifier;

    public ConfigScreen(Screen screen, String configIdentifier) {
        super(Text.translatable("config.screen." + configIdentifier + ".title"));
        this.screen = screen;
        this.configIdentifier = configIdentifier;
    }

    @Override
    protected void init() {
        configListWidget = new ConfigListWidget(this.client, this.width, this.height - 75, 43, 24, configIdentifier);
        addDrawableChild(configListWidget);

        TextFieldWidget searchField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 6, 200, 20, Text.translatable("config.gui." + Constants.MOD_ID +".search"));
        searchField.setMaxLength(50);
        searchField.setChangedListener(this::filterEntries);
        addDrawableChild(searchField);

        var width = 220;
        addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> close())
                .dimensions(this.width / 2 - width / 2, this.height - 27, width, 20)
                .build());
    }

    private void filterEntries(String search) {
        configListWidget.setFilter(search);
    }

    @Override
    public void close() {
        if (this.client != null) this.client.setScreen(screen);
    }
}
