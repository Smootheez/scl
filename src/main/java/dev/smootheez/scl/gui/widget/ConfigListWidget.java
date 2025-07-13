package dev.smootheez.scl.gui.widget;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.resources.language.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;

import java.util.*;

public class ConfigListWidget extends ContainerObjectSelectionList<ConfigWidgetEntry> {
    private final String configIdentifier;
    private String filter = "";

    public ConfigListWidget(Minecraft minecraft, int i, int j, int k, int l, String configIdentifier) {
        super(minecraft, i, j, k, l);
        this.configIdentifier = configIdentifier;
        updateEntries();
    }

    public boolean hasChanged() {
        for (ConfigWidgetEntry entry : this.children()) {
            if (entry instanceof LabeledWidgetEntry<?> widgetEntry && widgetEntry.hasChanged()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected int scrollBarX() {
        return this.width / 2 + 185;
    }

    @Override
    public int getRowWidth() {
        return 350;
    }

    public <T> ConfigWidgetEntry createWidget(ConfigOption<T> option) {
        List<FormattedCharSequence> description = createDescription(option);
        return option.getWidgetHandler().createWidget(option, description);
    }

    private List<FormattedCharSequence> createDescription(ConfigOption<?> option) {
        String descriptionKey = option.getTranslation() + ".description";
        Component translateable = Component.translatable(descriptionKey);
        if (I18n.exists(descriptionKey)) {
            return this.minecraft.font.split(translateable, 200);
        }
        return Collections.emptyList();
    }

    private void updateEntries() {
        clearEntries();
        List<ConfigOption<?>> configOptions = ConfigRegistry.getConfigOptions(configIdentifier);
        List<ConfigOption<?>> filteredOptions = configOptions.stream()
                .filter(option -> matchesSearchTerm(option, filter))
                .toList();
        for (ConfigOption<?> option : filteredOptions) {
            addEntry(createWidget(option));
        }
    }

    private boolean matchesSearchTerm(ConfigOption<?> option, String search) {
        String lowerCaseSearch = search.toLowerCase();

        String translation = option.getTranslation();
        String translatedText = Component.translatable(translation).toString();

        return translatedText.toLowerCase().contains(lowerCaseSearch) ||
                option.getKey().toLowerCase().contains(lowerCaseSearch);
    }

    public void search(String search) {
        this.filter = search;
        updateEntries();
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        super.renderWidget(guiGraphics, i, j, f);
        ConfigWidgetEntry hoveredWidget = this.getHovered();
        if (hoveredWidget != null && hoveredWidget.description != null && this.minecraft.screen != null)
            guiGraphics.setTooltipForNextFrame(hoveredWidget.description, i, j);
    }
}
