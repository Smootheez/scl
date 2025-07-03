package dev.smootheez.scl.gui.widget.entry;

import dev.smootheez.scl.config.*;
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

public abstract class LabeledWidgetEntry<T> extends ConfigWidgetEntry {
    private final List<FormattedCharSequence> label;
    protected final List<AbstractWidget> children = Lists.newArrayList();
    private final Font font = Minecraft.getInstance().font;
    protected final Button resetButton;
    protected final ConfigOption<T> option;
    private final T originalValue;

    public LabeledWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<T> option) {
        super(description);
        this.label = this.font.split(label, 350);
        this.option = option;
        this.originalValue = option.getValue();

        this.resetButton = Button.builder(Component.literal("⭮"), button -> this.resetButtonAction()).size(20, 20).build();
        this.children.add(this.resetButton);
    }

    public boolean hasChanged() {
        T currentValue = getValue();
        if (currentValue == null)
            return originalValue != null;
        return !currentValue.equals(originalValue);
    }

    public abstract T getValue();

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return this.children;
    }

    @Override
    public @NotNull List<? extends NarratableEntry> narratables() {
        return this.children;
    }

    public void resetButtonAction() {
        this.option.setValue(this.option.getDefaultValue());
        updateResetButton();
    }

    public void updateResetButton() {
        this.resetButton.active = !this.option.getValue().equals(this.option.getDefaultValue());
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
