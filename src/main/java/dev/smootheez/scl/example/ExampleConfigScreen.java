package dev.smootheez.scl.example;

import dev.smootheez.scl.widget.ConfigListWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ExampleConfigScreen extends Screen {
    private final Screen screen;
    protected ExampleConfigScreen(Screen screen) {
        super(Text.literal("Example Config"));
        this.screen = screen;
    }

    @Override
    protected void init() {
        super.init();
        ConfigListWidget configListWidget = new ConfigListWidget(this.client, this.width, this.height, 32, this.height - 26,24);

        addDrawableChild(configListWidget);
    }
}
