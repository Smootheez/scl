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
    private final Button resetButton;

    protected LabeledWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description) {
        super(description);
        this.label = this.font.split(label, 350);

        this.resetButton = Button.builder(Component.literal("⭮"), button -> this.resetButtonAction()).size(20, 20).build();
        this.children.add(this.resetButton);
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return this.children;
    }

    @Override
    public @NotNull List<? extends NarratableEntry> narratables() {
        return this.children;
    }

    public void resetButtonAction() {
    }

    public Button getResetButton() {
        return resetButton;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f) {
        renderLabel(guiGraphics, j, k);

        int xPos = k + l; // start from the right edge
        for (AbstractWidget child : this.children) {
            xPos -= child.getWidth(); // move left by the width of the child
            child.setX(xPos);
            child.setY(j);
            child.render(guiGraphics, n, o, f);
            xPos -= 3; // add some spacing between buttons
        }
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
