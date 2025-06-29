package dev.smootheez.scl.gui.widget;

import com.google.common.collect.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.*;
import net.minecraft.client.gui.narration.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public abstract class NamedConfigWidget extends AbstractConfigWidget{
    private final List<FormattedCharSequence> name;
    protected final List<AbstractWidget> children = Lists.newArrayList();
    private final Minecraft client = Minecraft.getInstance();

    protected NamedConfigWidget(Component name, @Nullable List<FormattedCharSequence> description) {
        super(description);
        this.name = this.client.font.split(name, 350);
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return this.children;
    }

    @Override
    public @NotNull List<? extends NarratableEntry> narratables() {
        return this.children;
    }

    protected void drawName(GuiGraphics guiGraphics, int x, int y) {
        if (this.name.size() == 1) {
            guiGraphics.drawString(client.font, this.name.get(0), x, y + 5, 0xFFFFFF);
        } else if (this.name.size() >= 2) {
            guiGraphics.drawString(client.font, this.name.get(0), x, y, 0xFFFFFF);
            guiGraphics.drawString(client.font, this.name.get(1), x, y + 10, 0xFFFFFF);
        }
    }
}
