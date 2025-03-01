package dev.smootheez.scl.widget;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ElementListWidget;

public class OptionListWidget extends ElementListWidget<AbstractConfigWidget> {
    public OptionListWidget(MinecraftClient minecraftClient, int width, int height, int v1, int v2, int entryHeight) {
        super(minecraftClient, width, height, v1, v2, entryHeight);
    }
}
