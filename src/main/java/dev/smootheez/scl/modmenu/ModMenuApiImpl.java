package dev.smootheez.scl.modmenu;

import com.terraformersmc.modmenu.api.*;
import dev.smootheez.scl.config.*;

import java.util.*;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        return ConfigRegistry.getConfigScreenFactories();
    }
}
