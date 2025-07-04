package dev.smootheez.scl.gui.widget;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.screen.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;

import java.util.*;

public class OptionListWidget extends ContainerObjectSelectionList<ConfigWidgetEntry> {
    protected final ConfigOption<OptionList> option;
    private final OptionListScreen screen;
    private String filter = "";

    public OptionListWidget(Minecraft minecraft, int i, int j, int k, int l, int m, ConfigOption<OptionList> option, OptionListScreen screen) {
        super(minecraft, i, j, k, l, m);
        this.option = option;
        this.screen = screen;
        updateEntries();
    }

    public void updateEntries() {
        clearEntries();
        List<String> list = option.getValue().values();
        List<String> filteredList = list.stream()
                .filter(s -> matchesSearchTerm(s, filter))
                .toList();
        for (String s : filteredList) {
            addEntry(new ValueListWidgetEntry(Component.literal(s), this.screen));
        }
    }

    private boolean matchesSearchTerm(String searchTerm, String value) {
        return searchTerm.toLowerCase().contains(value);
    }

    public void search(String search) {
        this.filter = search;
        updateEntries();
    }
}
