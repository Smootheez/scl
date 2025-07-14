package dev.smootheez.scl.gui.widget.entry;

import dev.smootheez.scl.gui.screen.*;
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

public class ValueListWidgetEntry extends ConfigWidgetEntry {
    private final List<FormattedCharSequence> label;
    protected final List<AbstractWidget> children = Lists.newArrayList();
    private final Font font = Minecraft.getInstance().font;

    public ValueListWidgetEntry(Component label, OptionListScreen optionListScreen) {
        super(null);
        this.label = this.font.split(label, 150);

        Button removeButton = Button.builder(Component.translatable("config.widget.scl.removeValue"),
                        button -> optionListScreen.handleRemoveValueButton(label.getString())
        ).size(44, 20).build();

        this.children.add(removeButton);
    }

    @Override
    public @NotNull List<? extends NarratableEntry> narratables() {
        return this.children;
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return this.children;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f) {
        renderLabel(guiGraphics, j, k);

        int xPos = k + l;
        for (AbstractWidget child : this.children) {
            xPos -= child.getWidth();
            child.setX(xPos);
            child.setY(j);
            child.render(guiGraphics, n, o, f);
            xPos -= 3;
        }
    }


    protected void renderLabel(GuiGraphics guiGraphics, int i, int j) {
        if (this.label.size() == 1) {
            guiGraphics.drawString(this.font, this.label.getFirst(), j, i + 5, -1);
        } else if (this.label.size() >= 2) {
            guiGraphics.drawString(this.font, this.label.get(0), j, i, -1);
            guiGraphics.drawString(this.font, this.label.get(1), j, i + 10, -1);
        }
    }
}
