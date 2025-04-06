package dev.smootheez.scl;

import dev.smootheez.scl.util.ModChecker;
import net.fabricmc.api.ClientModInitializer;

public class Scl implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        Constants.LOGGER.info("Hello Fabric world!");
        if (ModChecker.isModInstalled("modmenu")) Constants.LOGGER.info("Mod Menu is installed!");
        else Constants.LOGGER.info("Mod Menu is not installed!");
    }
}
