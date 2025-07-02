package dev.smootheez.scl.gui.widget;

import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;

public class OptionListWidget extends ContainerObjectSelectionList<ConfigWidgetEntry> {
    public OptionListWidget(Minecraft minecraft, int i, int j, int k, int l, int m) {
        super(minecraft, i, j, k, l, m);
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
