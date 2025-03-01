package dev.smootheez.scl.example;

import dev.smootheez.scl.registry.ConfigRegistry;
import dev.smootheez.scl.widget.ConfigListWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ExampleConfigScreen extends Screen {
    private final Screen screen;

    protected ExampleConfigScreen(Screen screen) {
        super(Text.literal("Example Config"));
        this.screen = screen;
    }

    @Override
    protected void init() {
        ConfigListWidget configListWidget = new ConfigListWidget(this.client, this.width, this.height, 36, this.height - 32,24);
        addDrawableChild(configListWidget);

        addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, button -> close())
                .dimensions(this.width / 2 + 5, this.height - 27, 150, 20)
                .build());
        addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> {
                    ConfigRegistry.save();
                    close();
                })
                .dimensions(this.width / 2 -155 , this.height - 27, 150, 20)
                .build());
    }

    @Override
    public void close() {
        if (this.client != null) this.client.setScreen(screen);
    }
}
