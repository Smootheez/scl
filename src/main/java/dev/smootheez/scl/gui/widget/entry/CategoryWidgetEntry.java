package dev.smootheez.scl.gui.widget.entry;

import com.google.common.collect.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.events.*;
import net.minecraft.client.gui.narration.*;
import net.minecraft.network.chat.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class CategoryWidgetEntry extends ConfigWidgetEntry {
    private final Component label;

    public CategoryWidgetEntry(Component label) {
        super(null);
        this.label = label;
    }

    @Override
    public @NotNull List<? extends NarratableEntry> narratables() {
        return ImmutableList.of(new NarratableEntry() {
            public NarratableEntry.@NotNull NarrationPriority narrationPriority() {
                return NarrationPriority.HOVERED;
            }

            public void updateNarration(NarrationElementOutput narrationElementOutput) {
                narrationElementOutput.add(NarratedElementType.TITLE, CategoryWidgetEntry.this.label);
            }
        });
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f) {
        guiGraphics.drawCenteredString(Minecraft.getInstance().font, this.label, k + l / 2, j + 5, -1);
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return ImmutableList.of();
    }
}
