package dev.smootheez.scl.gui.widget;

import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.*;
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

    public ConfigListWidget(Minecraft minecraft, int i, int j, int k, int l, int m, String configIdentifier) {
        super(minecraft, i, j, k, l, m);
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
    protected int getScrollbarPosition() {
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

    public void tick() {
        for (ConfigWidgetEntry entry : this.children()) {
            if (entry instanceof TextWidgetEntry<?> tickable)
                tickable.tick();
        }
    }

    private void updateEntries() {
        clearEntries();

        List<ConfigOption<?>> configOptions = ConfigRegistry.getConfigOptions(configIdentifier);
        List<ConfigOption<?>> filteredOptions = configOptions.stream()
                .filter(option -> matchesSearchTerm(option, filter))
                .toList();

        Map<String, List<ConfigOption<?>>> categorized = new TreeMap<>();
        List<ConfigOption<?>> rootOptions = new ArrayList<>();

        for (ConfigOption<?> option : filteredOptions) {
            String category = option.getCategory();
            if (category == null) {
                rootOptions.add(option);
            } else {
                categorized.computeIfAbsent(category, k -> new ArrayList<>()).add(option);
            }
        }

        for (ConfigOption<?> option : rootOptions) {
            addEntry(createWidget(option));
        }

        for (Map.Entry<String, List<ConfigOption<?>>> entry : categorized.entrySet()) {
            String categoryKey = entry.getKey();
            List<ConfigOption<?>> options = entry.getValue();

            String modId = options.get(0).getConfigIdentifier();
            Component categoryLabel = Component.translatable("config.option."+ modId +".categories." + categoryKey)
                    .withStyle(ChatFormatting.BOLD, ChatFormatting.AQUA, ChatFormatting.UNDERLINE);

            addEntry(new CategoryWidgetEntry(categoryLabel));

            for (ConfigOption<?> option : options) {
                addEntry(createWidget(option));
            }
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
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        ConfigWidgetEntry hoveredWidget = this.getHovered();
        if (hoveredWidget != null && hoveredWidget.description != null && this.minecraft.screen != null)
            this.minecraft.screen.setTooltipForNextRenderPass(hoveredWidget.description);
    }
}
