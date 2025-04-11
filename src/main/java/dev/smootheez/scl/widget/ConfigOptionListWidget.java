package dev.smootheez.scl.widget;

import dev.smootheez.scl.helper.OptionListHelper;
import dev.smootheez.scl.widget.entry.ListOptionEntries;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.text.Text;

public class ConfigOptionListWidget extends ElementListWidget<AbstractConfigWidget> {

    private String filter = "";

    public ConfigOptionListWidget(MinecraftClient minecraftClient, int width, int height, int v1, int v2) {
        super(minecraftClient, width, height, v1, v2);
        var optionList = OptionListHelper.getOptionList().getValue();

        for (int i = 0; i < optionList.values().size(); i++) {
            addEntry(new ListOptionEntries(Text.of(optionList.getValue(i)), this));
        }
    }

    public void addList(AbstractConfigWidget configWidget) {
        addEntry(configWidget);
    }

    public void clearList() {
        clearEntries();
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

    public void setFilter(String filter) {
        this.filter = filter.toLowerCase();
        updateEntries();
    }

    private void updateEntries() {
        clearEntries();
        var optionList = OptionListHelper.getOptionList().getValue();
        for (String value : optionList.values()) {
            if (value.toLowerCase().contains(filter)) addEntry(new ListOptionEntries(Text.of(value), this));
        }
    }
}
