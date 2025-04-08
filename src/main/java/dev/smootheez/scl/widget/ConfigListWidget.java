package dev.smootheez.scl.widget;

import com.google.common.collect.ImmutableList;
import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.registry.ConfigRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ConfigListWidget extends ElementListWidget<AbstractConfigWidget> {
    private String filter = "";
    private final String configIdentifier;

    public ConfigListWidget(MinecraftClient minecraftClient, int width, int height, int v1, int v2, int entryHeight, String configIdentifier) {
        super(minecraftClient, width, height, v1, v2, entryHeight);
        this.configIdentifier = configIdentifier;
        updateEntries();
    }

    public void setFilter (String filter) {
        this.filter = filter.toLowerCase();
        updateEntries();
    }

    private void updateEntries() {
        clearEntries();
        List<ConfigOption<?>> configOptions = ConfigRegistry.getConfigOptions(configIdentifier);
        List<ConfigOption<?>> filteredOptions = configOptions.stream()
                .filter(configOption -> matchesSearchTerm(configOption, filter))
                .toList();
        for (ConfigOption<?> option : filteredOptions) {
            addEntry(createWidget(option));
        }
    }

    private boolean matchesSearchTerm(ConfigOption<?> option, String searchTerm) {
        String lowercaseSearchTerm = searchTerm.toLowerCase();

        String translation = option.getTranslation();
        String translatedText = Text.translatable(translation).getString();

        return translatedText.toLowerCase().contains(lowercaseSearchTerm) || option.getKey().toLowerCase().contains(lowercaseSearchTerm);
    }

    public <T> AbstractConfigWidget createWidget(ConfigOption<T> option) {
        List<OrderedText> description = createDescriptionText(option);
        return option.getWidgetHandler().createWidget(option, description);
    }

    private List<OrderedText> createDescriptionText(ConfigOption<?> option) {
        String descriptionKey = option.getTranslation() + ".description";
        Text descriptionText = Text.translatable(descriptionKey);

        if (I18n.hasTranslation(descriptionKey)) {
            ImmutableList.Builder<OrderedText> builder = ImmutableList.builder();
            builder.add(Text.literal(option.getKey()).formatted(Formatting.YELLOW).asOrderedText());
            this.client.textRenderer.wrapLines(descriptionText, 200).forEach(builder::add);
            return builder.build();
        } else {
            return ImmutableList.of(Text.literal(option.getKey()).formatted(Formatting.YELLOW).asOrderedText());
        }
    }

    @Override
    protected int getScrollbarPositionX() {
        return this.width / 2 + 185;
    }

    @Override
    public int getRowWidth() {
        return 350;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        AbstractConfigWidget abstractConfigWidget = this.getHoveredEntry();
        if (abstractConfigWidget != null && abstractConfigWidget.description != null && this.client.currentScreen != null) {
            this.client.currentScreen.setTooltip(abstractConfigWidget.description);
        }
    }
}
