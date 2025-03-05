package dev.smootheez.scl.example;

import dev.smootheez.scl.Constants;
import dev.smootheez.scl.registry.ConfigRegistry;
import dev.smootheez.scl.widget.ConfigListWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ExampleConfigScreen extends Screen {
    private final Screen screen;
    private ConfigListWidget configListWidget;

    protected ExampleConfigScreen(Screen screen) {
        super(Text.translatable("screen." + ConfigRegistry.getModId() + ".title"));
        this.screen = screen;
    }

    @Override
    protected void init() {
        configListWidget = new ConfigListWidget(this.client, this.width, this.height, 36, this.height - 32, 24);
        addDrawableChild(configListWidget);

        TextFieldWidget searchField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 6, 200, 20, Text.translatable("gui." + Constants.MOD_ID +".search"));
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
