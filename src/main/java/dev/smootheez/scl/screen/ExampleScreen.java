package dev.smootheez.scl.screen;

import dev.smootheez.scl.gui.widget.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

import java.util.*;
import java.util.stream.*;

public class ExampleScreen extends Screen {
    private final Screen parent;
    private final List<Component> pages;
    private int currentPage;
    private ConfigListWidget listWidget;

    public ExampleScreen(Screen parent) {
        super(Component.literal("Example Screen")); //TODO: Change it into translatable
        this.parent = parent;
        this.pages = IntStream.range(1, 6)
                .mapToObj(i -> Component.literal("Page " + i))
                .collect(Collectors.toList());
    }

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();

        int listWidth = this.width - 115;
        int listX = this.width - listWidth;

        this.listWidget = new ConfigListWidget(this.minecraft, listWidth, this.height, 43, this.height - 32, 24);
        this.listWidget.setLeftPos(listX);
//        this.addRenderableWidget(listWidget);
        TextListWidget textListWidget = new TextListWidget(this.minecraft, listWidth, this.height, 43, this.height - 32, 24);
        textListWidget.setLeftPos(listX);
        this.addRenderableWidget(textListWidget);

        EditBox searchField = new EditBox(this.font, 10, 10, 100, 16, Component.literal("Search"));
        searchField.setMaxLength(50);
        //TODO: Implement search
        this.addRenderableWidget(searchField);

        this.addRenderableWidget(Button.builder(Component.literal("Done"), btn -> {
            onClose();
            //TODO: Save config
        }).pos(10, this.height - 75).size(100, 20).build()).active = false; //TODO: Enable when there is changes to save

        this.addRenderableWidget(Button.builder(Component.literal("Previous"), btn -> {
            if (currentPage > 0) {
                currentPage--;
                this.init();
            }
        }).pos(10, this.height - 50).size(48, 20).build()).active = currentPage > 0;

        this.addRenderableWidget(Button.builder(Component.literal("Next"), btn -> {
            if (currentPage < pages.size() - 1) {
                currentPage++;
                this.init();
            }
        }).pos(62, this.height - 50).size(48, 20).build()).active = currentPage < pages.size() - 1;

        this.addRenderableWidget(Button.builder(Component.literal("Cancel"), btn ->
                onClose()).pos(10, this.height - 25).size(100, 20).build());
    }

    /*private List<FormattedCharSequence> createDescription() {
        String description = "Example description";
        Component translatableDescription = Component.translatable(description);

        if (I18n.exists(description)) {
            ImmutableList.Builder<FormattedCharSequence> builder = ImmutableList.builder();
            builder.add(Component.literal("Example long description").withStyle(ChatFormatting.YELLOW).getVisualOrderText());
            if (this.minecraft != null) this.minecraft.font.split(translatableDescription, 50).forEach(builder::add);
            return builder.build();
        } else {
            return ImmutableList.of(Component.literal("Example long description").withStyle(ChatFormatting.YELLOW).getVisualOrderText());
        }
    }*/

    @Override
    public void onClose() {
        if (this.minecraft != null) this.minecraft.setScreen(parent);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, i, j, f);
//        guiGraphics.drawString(this.font, createDescription().get(0), 10, 40, 0xFFFFFF);

        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 10, 0xFFFFFF);
//        guiGraphics.drawCenteredString(this.font, pages.get(currentPage), this.width / 2, 40, 0xFFFFFF);
    }
}
