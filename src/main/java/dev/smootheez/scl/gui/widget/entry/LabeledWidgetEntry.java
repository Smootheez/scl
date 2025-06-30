package dev.smootheez.scl.gui.widget.entry;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.*;
import net.minecraft.client.gui.narration.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.apache.commons.compress.utils.*;
import org.jetbrains.annotations.*;

import java.util.*;

public abstract class LabeledWidgetEntry extends ConfigWidgetEntry {
    private final List<FormattedCharSequence> label;
    protected final List<AbstractWidget> children = Lists.newArrayList();
    private final Font font = Minecraft.getInstance().font;

    protected LabeledWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description) {
        super(description);
        this.label = this.font.split(label, 175);
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return this.children;
    }

    @Override
    public @NotNull List<? extends NarratableEntry> narratables() {
        return this.children;
    }

    protected void renderLabel(GuiGraphics guiGraphics, int i, int j) {
        if (this.label.size() == 1) {
            guiGraphics.drawString(this.font, this.label.get(0), j, i + 5, 16777215, false);
        } else if (this.label.size() >= 2) {
            guiGraphics.drawString(this.font, this.label.get(0), j, 1, 16777215, false);
            guiGraphics.drawString(this.font, this.label.get(0), j, 1 + 10, 16777215, false);
        }
    }
}
