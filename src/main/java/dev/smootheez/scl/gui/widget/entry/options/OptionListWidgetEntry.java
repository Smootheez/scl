package dev.smootheez.scl.gui.widget.entry.options;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.screen.*;
import dev.smootheez.scl.gui.widget.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.network.chat.*;

import java.util.*;

public class OptionListWidgetEntry extends LabeledWidgetEntry<OptionList> {
    protected final ValueHoldingButton<OptionList> button;

    public OptionListWidgetEntry(Component label, ConfigOption<OptionList> option) {
        super(label, null, option);

        this.button = ValueHoldingButton.builder(Component.translatable("config.widget.scl.editValue"),
                b -> {
                    handleValueChange();
                    updateResetButton();
                }, option.getValue()).size(80, 20).build();

        this.children.add(button);
        updateResetButton();
    }

    //TODO: Change it to actual implementation
    private void handleValueChange() {
        OptionList newValue = option.getValue();
        List<String> values = newValue.values();
        values.remove("example_value_2");
        var newValues = new OptionList(values);
        this.option.setValue(newValues);
        this.button.setValue(newValues);
    }

    @Override
    public void resetButtonAction() {
        super.resetButtonAction();
        button.setValue(this.option.getDefaultValue());
    }

    @Override
    public OptionList getValue() {
        return button.getValue();
    }
}
