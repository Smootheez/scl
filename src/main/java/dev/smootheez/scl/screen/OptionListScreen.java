package dev.smootheez.scl.screen;

import dev.smootheez.scl.Constants;
import dev.smootheez.scl.helper.OptionListHelper;
import dev.smootheez.scl.widget.ConfigOptionListWidget;
import dev.smootheez.scl.widget.entry.ListOptionEntries;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class OptionListScreen extends Screen {
    private final Screen screen;
    private ConfigOptionListWidget optionListWidget;

    public OptionListScreen(Screen screen) {
        super(Text.translatable("config.screen." + Constants.MOD_ID + ".title.optionList"));
        this.screen = screen;
    }

    @Override
    protected void init() {
        optionListWidget = new ConfigOptionListWidget(this.client, this.width, this.height - 75, 43, 24);
        addDrawableChild(optionListWidget);

        TextFieldWidget searchField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 6, 200, 20, Text.translatable("gui." + Constants.MOD_ID +".search"));
        searchField.setMaxLength(50);
        searchField.setChangedListener(this::filterEntries);
        addDrawableChild(searchField);

        var width = 150;
        var height = 20;
        addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> close())
                .dimensions(this.width / 2 - 155, this.height - 27, width, height)
                .build());
        addDrawableChild(ButtonWidget.builder(Text.translatable("config.gui." + Constants.MOD_ID + ".addOptionList"), action -> openAddListScreen())
                .dimensions(this.width / 2 + 5, this.height - 27, width, height)
                .build());
    }

    private void filterEntries(String filter) {
        optionListWidget.setFilter(filter);
    }

    private void openAddListScreen() {
        if (client != null) {
            var currentScreen = this.client.currentScreen;
            this.client.setScreen(new AddListScreen(currentScreen, optionListWidget, this));
        }
    }

    public void refreshList() {
        optionListWidget.clearList();
        var optionList = OptionListHelper.getOptionList().getValue();
        for (String value : optionList.values()) optionListWidget.addList(new ListOptionEntries(Text.of(value), optionListWidget));
    }

    @Override
    public void close() {
        if (this.client != null) this.client.setScreen(screen);
    }
}
