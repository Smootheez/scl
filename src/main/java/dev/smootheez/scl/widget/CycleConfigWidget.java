package dev.smootheez.scl.widget;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.helper.ConfigWidgetHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CycleConfigWidget<T extends Enum<T>> extends NamedConfigWidget {
    private final CyclingButtonWidget<T> cycleButton;
    private final ButtonWidget resetButton;
    private final ConfigOption<T> option;

    public CycleConfigWidget(Text name, @Nullable List<OrderedText> description, ConfigOption<T> option) {
        super(name, description);
        this.option = option;
        T[] enumValues = option.getType().getEnumConstants();

        cycleButton = CyclingButtonWidget.<T>builder(e -> Text.translatable(option.getTranslation() + "." + toCamelCase(e.name())))
                .omitKeyText()
                .values(enumValues)
                .initially(option.getValue())
                .build(10, 5, 80, 20, name, (button, value) -> {
                    option.setValue(value);
                    updateResetButtonState();
                });

        resetButton = ConfigWidgetHelper.createResetButton(this::resetValue);

        this.children.add(this.cycleButton);
        this.children.add(this.resetButton);

        updateResetButtonState();
    }

    private String toCamelCase(String enumName) {
        StringBuilder camelCaseName = new StringBuilder();
        String[] parts = enumName.toLowerCase().split("_");
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (!part.isEmpty()) {
                if (i == 0) {
                    camelCaseName.append(part);
                } else {
                    camelCaseName.append(part.substring(0, 1).toUpperCase())
                            .append(part.substring(1));
                }
            }
        }
        return camelCaseName.toString();
    }

    private void resetValue() {
        T defaultValue = option.getDefaultValue();
        option.setValue(defaultValue);
        cycleButton.setValue(defaultValue);
        updateResetButtonState();
    }

    private void updateResetButtonState() {
        resetButton.active = !option.getValue().equals(option.getDefaultValue());
    }

    @Override
    public List<? extends Selectable> selectableChildren() {
        return List.of(this.cycleButton);
    }

    @Override
    public List<? extends Element> children() {
        return List.of(this.cycleButton, this.resetButton);
    }

    @Override
    public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.drawName(context, x, y);

        this.cycleButton.setX(x + entryWidth - 105);
        this.cycleButton.setY(y);
        this.cycleButton.render(context, mouseX, mouseY, tickDelta);

        ConfigWidgetHelper.setResetButtonPosition(this.resetButton, context, x, y, entryWidth, mouseX, mouseY, tickDelta);
    }
}