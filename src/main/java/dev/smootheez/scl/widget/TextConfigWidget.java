package dev.smootheez.scl.widget;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.ConfigOptionList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TextConfigWidget extends NamedConfigWidget {
    private final TextFieldWidget textFieldWidget;
    private final ButtonWidget resetButton;
    private final ConfigOption<ConfigOptionList> option;

    public TextConfigWidget(Text name, @Nullable List<OrderedText> description, ConfigOption<ConfigOptionList> option) {
        super(name, description);
        this.option = option;

        textFieldWidget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 76, 16, name);
        textFieldWidget.setMaxLength(Integer.MAX_VALUE);
        textFieldWidget.setText(getConfigOptionListString(option.getValue()));
        textFieldWidget.setChangedListener(value -> {
            try {
                textFieldWidget.setEditableColor(14737632);
                option.setValue(stringToConfigOptionList(value));
                updateResetButtonState();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        resetButton = ButtonWidget.builder(Text.of("⭮"), button -> resetValue())
                .dimensions(0, 0, 20, 20)
                .build();

        this.children.add(textFieldWidget);
        this.children.add(resetButton);

        updateResetButtonState();
    }

    private ConfigOptionList stringToConfigOptionList(String value) {
        String cleanedValue = value.replace(" ", "").replace("\"", "");
        return new ConfigOptionList(List.of(cleanedValue.split(",")));
    }

    private String getConfigOptionListString(ConfigOptionList configOptionList) {
        StringBuilder sb = new StringBuilder();
        for (String option : configOptionList.values()) {
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
            sb.append(option);
        }
        return "\"" + sb + "\"";
    }

    private void resetValue() {
        ConfigOptionList defaultValue = option.getDefaultValue();
        textFieldWidget.setText(getConfigOptionListString(defaultValue));
        option.setValue(defaultValue);
        updateResetButtonState();
    }

    private void updateResetButtonState() {
        resetButton.active = !option.getValue().equals(option.getDefaultValue());
    }

    @Override
    public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
        this.drawName(context, x, y);

        this.textFieldWidget.setX(x + entryWidth - 103);
        this.textFieldWidget.setY(y + 2);
        this.textFieldWidget.render(context, mouseX, mouseY, tickDelta);

        this.resetButton.setX(x + entryWidth - 20);
        this.resetButton.setY(y);
        this.resetButton.render(context, mouseX, mouseY, tickDelta);
    }
}