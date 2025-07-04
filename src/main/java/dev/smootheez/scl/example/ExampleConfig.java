package dev.smootheez.scl.example;

import dev.smootheez.scl.api.*;
import dev.smootheez.scl.config.*;

@Config(name = "example_config", gui = true)
public class ExampleConfig {
    public static final ConfigOption<Integer> INT_TEXT = ConfigOption.create("intText", 0, -5, 100);
    public static final ConfigOption<Double> DOUBLE_TEXT = ConfigOption.create("doubleText", 0.0, 0.0, 2.0);
    public static final ConfigOption<Integer> INT_SLIDER = ConfigOption.create("intSlider", 0, -5, 100).asSlider();
    public static final ConfigOption<Double> DOUBLE_SLIDER = ConfigOption.create("doubleSlider", 0.0, 0.0, 2.0).asSlider();
    public static final ConfigOption<Double> DOUBLE_SLIDER_PERCENTAGE = ConfigOption.create("doubleSliderPercentage", 0.0, 0.0, 2.0).asSliderPercentage();
    public static final ConfigOption<Boolean> BOOLEAN = ConfigOption.create("boolean", false);
    public static final ConfigOption<OptionList> LIST = ConfigOption.create("list", "example_value_1", "example_value_2", "example_value_3");
    public static final ConfigOption<ExampleEnum> ENUM = ConfigOption.create("enum", ExampleEnum.EXAMPLE_VALUE_1);
}
