package dev.smootheez.scl.screen;

import dev.smootheez.scl.widget.ConfigOptionListWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class AddListScreen extends Screen {
    private final Screen screen;
    private final ConfigOptionListWidget optionListWidget;

    protected AddListScreen(Screen screen, ConfigOptionListWidget optionListWidget) {
        super(Text.of("Add List Screen"));
        this.screen = screen;
        this.optionListWidget = optionListWidget;
    }

    @Override
    protected void init() {
        super.init();

        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, action -> close())
                .dimensions(this.width / 2 - 100, this.height / 2, 200, 20)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        if (client != null) this.client.setScreen(screen);
    }
}
