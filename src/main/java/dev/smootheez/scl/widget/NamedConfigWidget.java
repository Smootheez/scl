package dev.smootheez.scl.widget;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class NamedConfigWidget extends AbstractConfigWidget{
    private final List<OrderedText> name;
    protected final List<ClickableWidget> children = Lists.newArrayList();
    private final MinecraftClient client = MinecraftClient.getInstance();

    protected NamedConfigWidget(Text name, @Nullable List<OrderedText> description) {
        super(description);
        this.name = this.client.textRenderer.wrapLines(name, 350);
    }

    /**
     * Returns the list of children of this widget.
     *
     * @return the list of children of this widget
     */
    @Override
    public List<? extends Element> children() {
        return this.children;
    }

    /**
     * Returns the list of selectable children of this widget. This is a
     * convenience method and is equivalent to calling {@link #children()}.
     *
     * @return the list of selectable children of this widget
     */
    @Override
    public List<? extends Selectable> selectableChildren() {
        return this.children;
    }

    /**
     * Draws the name of the widget at the specified coordinates.
     * If the name consists of a single line, it is drawn with a basic offset.
     * If the name has two or more lines, the first two lines are drawn with different offsets and colors.
     *
     * @param context the drawing context used to render the text
     * @param x the x-coordinate for the text
     * @param y the y-coordinate for the text
     */
    protected void drawName(DrawContext context, int x, int y) {
        if (this.name.size() == 1) {
            context.drawText(this.client.textRenderer, this.name.get(0), x, y + 5, 16777215, false);
        } else if (this.name.size() >= 2) {
            context.drawText(this.client.textRenderer, this.name.get(0), x, y, 16777215, false);
            context.drawText(this.client.textRenderer, this.name.get(1), x, y + 10, 8421504, false);
        }
    }
}
