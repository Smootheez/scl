package dev.smootheez.scl.screen;

import dev.smootheez.scl.Constants;
import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.OptionList;
import dev.smootheez.scl.helper.OptionListHelper;
import dev.smootheez.scl.widget.ConfigOptionListWidget;
import dev.smootheez.scl.widget.entry.ListOptionEntries;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class AddListScreen extends Screen {
    private final Screen screen;
    private final ConfigOptionListWidget optionListWidget;
    private TextFieldWidget textFieldWidget;
    private String value;
    private final ConfigOption<OptionList> option;
    private final OptionListScreen optionListScreen;

    protected AddListScreen(Screen screen, ConfigOptionListWidget optionListWidget, OptionListScreen optionListScreen) {
        super(Text.of("Add List Screen"));
        this.screen = screen;
        this.optionListWidget = optionListWidget;
        this.optionListScreen = optionListScreen;
        this.option = OptionListHelper.getOptionList();
    }

    @Override
    protected void init() {
        super.init();

        if (client == null) return;
        var buttonWidth = 200;
        var buttonHeight = 20;
        var buttonPositionX = this.width / 2 - buttonWidth / 2;

        textFieldWidget = new TextFieldWidget(this.client.textRenderer, buttonPositionX, this.height / 2 - 25, buttonWidth, buttonHeight, Text.translatable("config.gui.scl.enterValue"));
        textFieldWidget.setChangedListener( listener -> {
            try {
                textFieldWidget.setEditableColor(14737632);
                value = listener;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        addDrawableChild(textFieldWidget);
        addDrawableChild(ButtonWidget.builder(Text.translatable("config.gui." + Constants.MOD_ID + ".addOptionList"), action -> {
                    close();
                    addWidget();
                })
                .dimensions(buttonPositionX, this.height / 2, buttonWidth, buttonHeight)
                .build());
        addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, action -> close())
                .dimensions(buttonPositionX, this.height / 2 + 25, buttonWidth, buttonHeight)
                .build());
    }

    private void addWidget() {
        OptionList currentList = OptionListHelper.getOptionList().getValue();
        List<String> values = new ArrayList<>(currentList.values());
        values.add(value);
        optionListWidget.addList(new ListOptionEntries(Text.of(value), optionListWidget));
        option.setValue(new OptionList(values));
        optionListScreen.refreshList();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        if (client != null) this.client.setScreen(screen);
    }
}
