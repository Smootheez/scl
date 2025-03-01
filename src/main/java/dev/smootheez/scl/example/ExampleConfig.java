package dev.smootheez.scl.example;

import dev.smootheez.scl.annotation.Config;
import dev.smootheez.scl.api.ConfigProvider;
import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.ConfigOptionList;

@Config("exampleConfig")
public class ExampleConfig implements ConfigProvider {
    private static final ExampleConfig INSTANCE = new ExampleConfig();

    private final ConfigOption<Boolean> exampleBoolean = ConfigOption.create("exampleBoolean", false);
    private final ConfigOption<Boolean> exampleBoolean1 = ConfigOption.create("exampleBoolean1", false);
    private final ConfigOption<Boolean> exampleBoolean2 = ConfigOption.create("exampleBoolean2", false);
    private final ConfigOption<Boolean> exampleBoolean3 = ConfigOption.create("exampleBoolean3", false);
    private final ConfigOption<ConfigOptionList> exampleString = ConfigOption.create("exampleString", "exampleString, exampleString1, exampleString2, exampleString3");

    public static ExampleConfig getInstance() {
        return INSTANCE;
    }

    public ConfigOption<Boolean> getExampleBoolean() {
        return exampleBoolean;
    }
}
