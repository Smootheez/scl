package dev.smootheez.scl.example;

import com.terraformersmc.modmenu.api.*;
import dev.smootheez.scl.config.*;
import dev.smootheez.scl.proto.*;
import dev.smootheez.scl.screen.*;

import java.util.*;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ExampleScreen::new;
    }

    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        return ConfigRegistry.getConfigScreenFactories();
    }
}
