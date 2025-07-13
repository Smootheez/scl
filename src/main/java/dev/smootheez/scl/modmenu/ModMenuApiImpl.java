package dev.smootheez.scl.modmenu;

import com.terraformersmc.modmenu.api.*;
import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.screen.*;

import java.util.*;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> new ConfigScreen(screen, "example_config");
    }

    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        return ConfigRegistry.getConfigScreenFactories();
    }
}
