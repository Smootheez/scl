package dev.smootheez.scl.helper;

import dev.smootheez.scl.config.ConfigOption;
import dev.smootheez.scl.config.option.OptionList;

public class OptionListHelper {
    private static ConfigOption<OptionList> optionList;

    public static ConfigOption<OptionList> getOptionList() {
        return optionList;
    }

    public static void setOptionList(ConfigOption<OptionList> optionList) {
        OptionListHelper.optionList = optionList;
    }
}
