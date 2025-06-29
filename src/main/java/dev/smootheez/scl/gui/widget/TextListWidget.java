package dev.smootheez.scl.gui.widget;

import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

public class TextListWidget extends ContainerObjectSelectionList<TextSelectionEntry> {
    public TextListWidget(Minecraft minecraft, int i, int j, int k, int l, int m) {
        super(minecraft, i, j, k, l, m);
        for (int n = 0; n < 100; ++n) {
            this.addEntry(new TextSelectionEntry(Component.literal("Test " + n)));
        }
    }
}
