package dev.smootheez.scl.widget.entry;

import dev.smootheez.scl.widget.AbstractConfigWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.List;

public class AddOptionListEntries extends AbstractConfigWidget {
    private final ButtonWidget addOptionList;

    public AddOptionListEntries() {
        super(null);
        addOptionList = ButtonWidget.builder(Text.translatable("config.scl.addOptionList"), action ->
                System.out.println("Hello World"))
                .dimensions(0, 0, 250, 20)
                .build();
    }

    @Override
    public List<? extends Selectable> selectableChildren() {
        return List.of(this.addOptionList);
    }

    @Override
    public List<? extends Element> children() {
        return List.of(this.addOptionList);
    }

    @Override
    public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.addOptionList.setX(x);
        this.addOptionList.setY(y);
        this.addOptionList.render(context, mouseX, mouseY, tickDelta);
    }
}
