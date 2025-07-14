package dev.smootheez.scl.gui.screen;

import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.layouts.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.*;

public abstract class BaseConfigScreen extends Screen {
    protected final Screen parent;
    protected EditBox searchField;
    public final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);

    public BaseConfigScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        LinearLayout linearLayout = this.layout.addToHeader(LinearLayout.horizontal().spacing(8));
        this.searchField = linearLayout.addChild(new EditBox(this.font,100, 20, Component.translatable("config.widget.scl.search")));
        this.searchField.setMaxLength(50);
        this.searchField.setResponder(this::handleSearchField);
        this.setFocused(this.searchField);

        this.layout.visitWidgets(this::addRenderableWidget);
        this.repositionElements();
    }

    protected abstract void handleSearchField(String search);

    @Override
    public void onClose() {
        if (this.minecraft != null) this.minecraft.setScreen(parent);
    }
}
