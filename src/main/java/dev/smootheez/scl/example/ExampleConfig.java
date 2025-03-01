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
    private final ConfigOption<Integer> exampleInteger = ConfigOption.create("exampleInteger", 1, 0, 5);
    private final ConfigOption<Double> exampleDouble = ConfigOption.create("exampleDouble", 1.0, 0.0, 5.0);
    private final ConfigOption<ExampleEnum> exampleEnum = ConfigOption.create("exampleEnum", ExampleEnum.ONE);
    private final ConfigOption<ConfigOptionList> exampleString = ConfigOption.create("exampleString", "exampleString, exampleString1, exampleString2, exampleString3");

    public static ExampleConfig getInstance() {
        return INSTANCE;
    }
}
