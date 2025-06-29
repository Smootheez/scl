package dev.smootheez.scl.gui.widget.entry;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.*;
import net.minecraft.client.gui.narration.*;
import net.minecraft.network.chat.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class TextSelectionEntry extends ContainerObjectSelectionList.Entry<TextSelectionEntry> {
    private final Component name;

    public TextSelectionEntry(Component name) {
        this.name = name;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height,
                       int mouseX, int mouseY, boolean hovered, float partialTick) {
        Font font = Minecraft.getInstance().font;
        guiGraphics.drawString(font, name, left + 4, top + (height - 9) / 2, 0xFFFFFF, false);
    }

    @Override
    public @NotNull List<? extends NarratableEntry> narratables() {
        return List.of();
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return List.of();
    }
}
