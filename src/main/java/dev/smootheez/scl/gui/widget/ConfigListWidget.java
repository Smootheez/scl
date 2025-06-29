package dev.smootheez.scl.gui.widget;

import dev.smootheez.scl.gui.widget.entry.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class ConfigListWidget extends ContainerObjectSelectionList<AbstractConfigWidget> {
    public ConfigListWidget(Minecraft minecraft, int i, int j, int k, int l, int m) {
        super(minecraft, i, j, k, l, m);
        for (int n = 0; n < 100; ++n) {
            this.addEntry(new ButtonExample(Component.literal("Button " + n), null));
        }
    }

    @Override
    protected int getScrollbarPosition() {
        return this.width / 2 + 185;
    }

    @Override
    public int getRowLeft() {
        return 120;
    }

    @Override
    public int getRowWidth() {
        return 350;
    }

    static class ButtonExample extends NamedConfigWidget {
        private final CycleButton<Boolean> button;

        protected ButtonExample(Component name, @Nullable List<FormattedCharSequence> description) {
            super(name, description);
            boolean bl = false;
            button = CycleButton.onOffBuilder(bl)
//                    .displayOnlyValue()
                    .create(10, 5, 200, 20, Component.literal("Button"), (cycleButton, object) -> {
                        System.out.println("Clicked");
                    });

            this.children.add(this.button);
        }

        @Override
        public void render(GuiGraphics guiGraphics, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f) {
//            this.drawName(guiGraphics, j, k);

            this.button.setX(k);
            this.button.setY(j);
            this.button.render(guiGraphics, n, o, f);
        }
    }
}
