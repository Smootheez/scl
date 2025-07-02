package dev.smootheez.scl.gui.widget;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

public class OptionListWidget extends ContainerObjectSelectionList<ConfigWidgetEntry> {
    protected final ConfigOption<OptionList> option;

    public OptionListWidget(Minecraft minecraft, int i, int j, int k, int l, int m, ConfigOption<OptionList> option) {
        super(minecraft, i, j, k, l, m);
        this.option = option;
        var optionList = option.getValue().values();

        for (String s : optionList) {
            addEntry(new ValueListWidgetEntry(Component.literal(s), this, option));
        }
    }

    public void addList(ConfigWidgetEntry entry) {
        addEntry(entry);
    }

    public void clearList() {
        clearEntries();
    }

    public void removeList(ConfigWidgetEntry entry) {
        removeEntry(entry);
    }
}
