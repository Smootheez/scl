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
    public static final ConfigOption<String> STRING = ConfigOption.create("string", "");
    public static final ConfigOption<ExampleEnum> ENUM = ConfigOption.create("enum", ExampleEnum.EXAMPLE_VALUE_1);

    @Config.Category(ConfigCategory.CATEGORY_1)
    public static final ConfigOption<Integer> INT_TEXT_1 = ConfigOption.create("intText1", 0, -5, 100);
    public static final ConfigOption<Double> DOUBLE_TEXT_1 = ConfigOption.create("doubleText1", 0.0, 0.0, 2.0);
    public static final ConfigOption<Integer> INT_SLIDER_1 = ConfigOption.create("intSlider1", 0, -5, 100).asSlider();
    public static final ConfigOption<Double> DOUBLE_SLIDER_1 = ConfigOption.create("doubleSlider1", 0.0, 0.0, 2.0).asSlider();
    public static final ConfigOption<Double> DOUBLE_SLIDER_PERCENTAGE_1 = ConfigOption.create("doubleSliderPercentage1", 0.0, 0.0, 2.0).asSliderPercentage();
    public static final ConfigOption<Boolean> BOOLEAN_1 = ConfigOption.create("boolean1", false);
    public static final ConfigOption<OptionList> LIST_1 = ConfigOption.create("list1", "example_value_1", "example_value_2", "example_value_3");
    public static final ConfigOption<ExampleEnum> ENUM_1 = ConfigOption.create("enum1", ExampleEnum.EXAMPLE_VALUE_1);

    @Config.Category(ConfigCategory.CATEGORY_2)
    public static final ConfigOption<Integer> INT_TEXT_2 = ConfigOption.create("intText2", 0, -5, 100);
    public static final ConfigOption<Double> DOUBLE_TEXT_2 = ConfigOption.create("doubleText2", 0.0, 0.0, 2.0);
    public static final ConfigOption<Integer> INT_SLIDER_2 = ConfigOption.create("intSlider2", 0, -5, 100).asSlider();
    public static final ConfigOption<Double> DOUBLE_SLIDER_2 = ConfigOption.create("doubleSlider2", 0.0, 0.0, 2.0).asSlider();
    public static final ConfigOption<Double> DOUBLE_SLIDER_PERCENTAGE_2 = ConfigOption.create("doubleSliderPercentage2", 0.0, 0.0, 2.0).asSliderPercentage();
    public static final ConfigOption<Boolean> BOOLEAN_2 = ConfigOption.create("boolean2", false);
    public static final ConfigOption<OptionList> LIST_2 = ConfigOption.create("list2", "example_value_1", "example_value_2", "example_value_3");
    public static final ConfigOption<ExampleEnum> ENUM_2 = ConfigOption.create("enum2", ExampleEnum.EXAMPLE_VALUE_1);
}
