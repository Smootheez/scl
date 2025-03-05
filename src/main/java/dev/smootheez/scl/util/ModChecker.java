package dev.smootheez.scl.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public class ModChecker {
    public static boolean isModInstalled(String modId) {
        if (modId == null || modId.isEmpty()) return false;
        for (ModContainer mod : FabricLoader.getInstance().getAllMods())
            if (mod.getMetadata().getId().equals(modId))
                return true;
        return false;
    }
}
