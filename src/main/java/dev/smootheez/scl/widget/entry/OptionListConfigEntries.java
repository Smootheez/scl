package dev.smootheez.scl.widget.entry;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.OptionList;
import dev.smootheez.scl.helper.ConfigWidgetHelper;
import dev.smootheez.scl.screen.OptionListScreen;
import dev.smootheez.scl.widget.NamedConfigWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OptionListConfigEntries extends NamedConfigWidget {
    private final ButtonWidget openOptionListScreen;
    private final ButtonWidget resetButton;
    private final ConfigOption<OptionList> option;

    public OptionListConfigEntries(Text name, @Nullable List<OrderedText> description, ConfigOption<OptionList> option) {
        super(name, description);
        this.option = option;

        this.openOptionListScreen = ButtonWidget.builder(Text.translatable("options.scl.editOptionList"), button -> {
                    var client = MinecraftClient.getInstance();
                    Screen currentScreen = client.currentScreen;
                    if (currentScreen != null) {
                        client.setScreen(new OptionListScreen(currentScreen, this.option));
                    }
                })
                .dimensions(0, 0,80, 20).build();
        resetButton = ConfigWidgetHelper.createResetButton(this::resetValue);

        this.children.add(openOptionListScreen);
        this.children.add(resetButton);
    }

    private void resetValue() {
    }

    @Override
    public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.drawName(context, x, y);

        this.openOptionListScreen.setX(x + entryWidth - 105);
        this.openOptionListScreen.setY(y);
        this.openOptionListScreen.render(context, mouseX, mouseY, tickDelta);

        ConfigWidgetHelper.setResetButtonPosition(this.resetButton, context, x, y, entryWidth, mouseX, mouseY, tickDelta);
    }
}
