package dev.smootheez.scl.example;

import com.terraformersmc.modmenu.api.*;
import dev.smootheez.scl.config.*;
import dev.smootheez.scl.gui.screen.*;

import java.util.*;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigScreen(parent, "example_config");
    }

    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        return ConfigRegistry.getConfigScreenFactories();
    }
}
