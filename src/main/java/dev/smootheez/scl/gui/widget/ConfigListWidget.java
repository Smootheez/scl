package dev.smootheez.scl.gui.widget;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;

public class ConfigListWidget extends ContainerObjectSelectionList<ConfigWidgetEntry> {
    public ConfigListWidget(Minecraft minecraft, int i, int j, int k, int l, int m, String configIdentifier) {
        super(minecraft, i, j, k, l, m);

        for (ConfigOption<?> option : ConfigRegistry.getConfigOptions(configIdentifier)) {
            addEntry(createWidget(option));
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

    public <T> ConfigWidgetEntry createWidget(ConfigOption<T> option) {
        return option.getWidgetHandler().createWidget(option, null);
    }

    public void tick() {
        for (ConfigWidgetEntry entry : this.children()) {
            if (entry instanceof TextWidgetEntry<?> tickable) {
                tickable.tick();
            }
        }
    }

    public void search(String search) {
    }
}
