package dev.smootheez.scl.widget;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.OptionList;
import dev.smootheez.scl.widget.entry.AddOptionListEntries;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ElementListWidget;

public class ConfigOptionListWidget extends ElementListWidget<AbstractConfigWidget> {
    private final OptionList optionList;

    public ConfigOptionListWidget(MinecraftClient minecraftClient, int width, int height, int v1, int v2, int entryHeight, ConfigOption<OptionList> option) {
        super(minecraftClient, width, height, v1, v2, entryHeight);
        this.optionList = option.getValue();

        addEntry(new AddOptionListEntries());
    }

    @Override
    protected int getScrollbarPositionX() {
        return this.width / 2 + 135;
    }

    @Override
    public int getRowWidth() {
        return 250;
    }
}
