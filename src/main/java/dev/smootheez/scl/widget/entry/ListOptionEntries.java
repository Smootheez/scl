package dev.smootheez.scl.widget.entry;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.OptionList;
import dev.smootheez.scl.helper.ConfigWidgetHelper;
import dev.smootheez.scl.helper.OptionListHelper;
import dev.smootheez.scl.widget.NamedConfigWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ListOptionEntries extends NamedConfigWidget {
    private final ButtonWidget editValue;
    private final ButtonWidget removeButton;
    private final ConfigOption<OptionList> option;

    public ListOptionEntries(Text name) {
        super(name, null);
        this.option = OptionListHelper.getOptionList();

        this.editValue = ButtonWidget.builder(Text.translatable("config.scl.editOptionList"), action -> System.out.println("Hello World"))
                .dimensions(0, 0, 80, 20)
                .build();
        this.removeButton = ButtonWidget.builder(Text.of("-"), action -> removeValue(name.getString()))
                .dimensions(0, 0, 20, 20)
                .build();

        this.children.add(editValue);
        this.children.add(removeButton);
    }

    private void removeValue(String value) {
        OptionList currentList = option.getValue();
        List<String> values = new ArrayList<>(currentList.values());
        values.remove(value);
        option.setValue(new OptionList(values));
    }

    @Override
    public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.drawName(context, x, y);

        this.editValue.setX(x + entryWidth - 105);
        this.editValue.setY(y);
        this.editValue.render(context, mouseX, mouseY, tickDelta);

        ConfigWidgetHelper.setResetButtonPosition(this.removeButton, context, x, y, entryWidth, mouseX, mouseY, tickDelta);
    }
}
