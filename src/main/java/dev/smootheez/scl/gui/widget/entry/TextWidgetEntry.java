package dev.smootheez.scl.gui.widget.entry;

import dev.smootheez.scl.config.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class TextWidgetEntry<T> extends LabeledWidgetEntry {
    protected final ConfigOption<T> option;
    protected final EditBox editBox;

    public TextWidgetEntry(Component label, @Nullable List<FormattedCharSequence> description, ConfigOption<T> option) {
        super(label, description);
        this.option = option;

        this.editBox = new EditBox(Minecraft.getInstance().font, 0, 0, 80, 20, Component.literal(""));
        this.editBox.setValue(option.getValue().toString());
        this.editBox.setResponder(this::onTextChange);

        this.children.add(this.editBox);
    }

    @Override
    public void updateResetButton() {
        T currentValue = option.getValue();
        T defaultValue = option.getDefaultValue();
        getResetButton().active = !currentValue.equals(defaultValue);
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

}
