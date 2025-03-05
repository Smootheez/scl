package dev.smootheez.scl.widget;

import dev.smootheez.scl.helper.OptionListHelper;
import dev.smootheez.scl.widget.entry.ListOptionEntries;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.text.Text;

public class ConfigOptionListWidget extends ElementListWidget<AbstractConfigWidget> {

    public ConfigOptionListWidget(MinecraftClient minecraftClient, int width, int height, int v1, int v2, int entryHeight) {
        super(minecraftClient, width, height, v1, v2, entryHeight);
        var optionList = OptionListHelper.getOptionList().getValue();

        for (int i = 0; i < optionList.values().size(); i++) {
            addEntry(new ListOptionEntries(Text.of(optionList.getValue(i)), this));
        }
    }

    public void addList(AbstractConfigWidget configWidget) {
        addEntry(configWidget);
    }

    public void removeList(AbstractConfigWidget configWidget) {
        removeEntry(configWidget);
    }

    @Override
    protected int getScrollbarPositionX() {
        return this.width / 2 + 185;
    }

    @Override
    public int getRowWidth() {
        return 350;
    }
}
