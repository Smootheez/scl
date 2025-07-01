package dev.smootheez.scl.gui.widget;

import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;

public class ConfigListWidget extends ContainerObjectSelectionList<ConfigWidgetEntry> {
    public ConfigListWidget(Minecraft minecraft, int i, int j, int k, int l, int m) {
        super(minecraft, i, j, k, l, m);
        for (int z = 0; z < 100; z++) {
            this.addEntry(new BooleanWidgetEntry());
        }
    }

    @Override
    protected int getScrollbarPosition() {
        return this.width / 2 + 185;
    }

    @Override
    public int getRowWidth() {
        return 350;
    }
}
