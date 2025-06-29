package dev.smootheez.scl;

import dev.smootheez.scl.api.*;
import dev.smootheez.scl.config.*;
import dev.smootheez.scl.util.*;
import net.fabricmc.api.*;
import net.fabricmc.loader.api.*;

import java.util.*;

@Environment(EnvType.CLIENT)
public class SmootheezConfigLib implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Constants.LOGGER.info("Mod initialized");

		Collection<Object> configs = FabricLoader.getInstance()
				.getEntrypoints(Constants.MOD_ID, Object.class);
		for (Object config : configs) {
			Class<?> configClass = config.getClass();
			if (configClass.isAnnotationPresent(Config.class))
				ConfigRegistry.registerConfigs(configClass);
		}
	}
}