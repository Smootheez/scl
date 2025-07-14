package dev.smootheez.scl.gui.widget.entry;

import dev.smootheez.scl.config.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public abstract class TextWidgetEntry<T extends Number> extends LabeledWidgetEntry<T> {
    protected final EditBox editBox;

    public TextWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<T> option) {
        super(label, description, option);

        this.editBox = new EditBox(Minecraft.getInstance().font, 0, 0, 80, 20, Component.literal(""));
        this.editBox.setValue(option.getValue().toString());
        this.editBox.setResponder(this::onTextChange);

        this.children.add(this.editBox);
        updateResetButton();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f) {
        renderLabel(guiGraphics, j, k);

        var resetButtonWidth = resetButton.getWidth();
        this.resetButton.setX(k + l - resetButtonWidth);
        this.resetButton.setY(j);
        this.resetButton.render(guiGraphics, n, o, f);

        this.editBox.setX(k + l - editBox.getWidth() - 3 - resetButtonWidth);
        this.editBox.setY(j);
        this.editBox.render(guiGraphics, n, o, f);
    }

    @Override
    public void updateResetButton() {
        T currentValue = option.getValue();
        T defaultValue = option.getDefaultValue();
        this.resetButton.active = !currentValue.equals(defaultValue);
    }

    @Override
    public void resetButtonAction() {
        T defaultValue = option.getDefaultValue();
        option.setValue(defaultValue);
        this.editBox.setValue(defaultValue.toString());
        this.editBox.setTextColor(14737632);
        updateResetButton();
    }

    protected void onTextChange(String value) {
        updateResetButton();
    }

    public EditBox getEditBox() {
        return editBox;
    }
}
