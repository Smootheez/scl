package dev.smootheez.scl.gui.widget;

import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;

public class ConfigListWidget extends ContainerObjectSelectionList<ConfigWidgetEntry> {
    public ConfigListWidget(Minecraft minecraft, int i, int j, int k, int l, int m) {
        super(minecraft, i, j, k, l, m);
    }
}
