package dev.smootheez.scl.widget.entry;

import dev.smootheez.scl.Constants;
import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.OptionList;
import dev.smootheez.scl.helper.OptionListHelper;
import dev.smootheez.scl.widget.ConfigOptionListWidget;
import dev.smootheez.scl.widget.NamedConfigWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ListOptionEntries extends NamedConfigWidget {
    private final ButtonWidget removeButton;
    private final ConfigOption<OptionList> option;
    private final ConfigOptionListWidget listWidget;

    public ListOptionEntries(Text name, ConfigOptionListWidget listWidget) {
        super(name, null);
        this.listWidget = listWidget;
        this.option = OptionListHelper.getOptionList();

        this.removeButton = ButtonWidget.builder(Text.translatable("config.gui." + Constants.MOD_ID + ".removeOptionList"), action -> removeValue(name.getString()))
                .dimensions(0, 0, 80, 20)
                .build();

        this.children.add(removeButton);
    }

    private void removeValue(String value) {
        OptionList currentList = option.getValue();
        List<String> values = new ArrayList<>(currentList.values());
        values.remove(value);
        this.listWidget.removeList(this);
        option.setValue(new OptionList(values));
    }

    @Override
    public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.drawName(context, x, y);

        this.removeButton.setX(x + entryWidth - 80);
        this.removeButton.setY(y);
        this.removeButton.render(context, mouseX, mouseY, tickDelta);
    }
}
