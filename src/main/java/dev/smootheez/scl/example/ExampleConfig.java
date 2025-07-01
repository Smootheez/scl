package dev.smootheez.scl.example;

import dev.smootheez.scl.api.*;
import dev.smootheez.scl.config.*;

@Config(name = "example_config", gui = true)
public class ExampleConfig {
    public static final ConfigOption<Integer> EXAMPLE_INT = ConfigOption.create("example_int", 0, 0, 100);
    public static final ConfigOption<Boolean> EXAMPLE_BOOL = ConfigOption.create("example_bool", false);
    public static final ConfigOption<Double> EXAMPLE_DOUBLE = ConfigOption.create("example_double", 0.0, 0.0, 1.0);
    public static final ConfigOption<OptionList> EXAMPLE_LIST = ConfigOption.create("example_list", "example_value_1", "example_value_2", "example_value_3");
    public static final ConfigOption<ExampleEnum> EXAMPLE_ENUM = ConfigOption.create("example_enum", ExampleEnum.EXAMPLE_VALUE_1);
}
