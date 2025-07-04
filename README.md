# Smootheez Config Lib (SCL)

A utility mod designed to simplify configuration handling for Minecraft modding.
Primarily developed for my own mods, but available for public use.

## What is SCL?

SCL provides an easier way to manage configurations for Minecraft mods.
It handles the boilerplate code and provides a clean interface for:
- Config file management
- Automatic config screen generation
- Automatic configuration generation

## Preview In Game
### Normal preview of config screen
![preview](https://raw.githubusercontent.com/Smootheez/scl/refs/heads/dev/assets/image/list_option.png)
### Preview of config screen when the widget have description
![preview](https://raw.githubusercontent.com/Smootheez/scl/refs/heads/dev/assets/image/list_option_with_description.png)
### Preview of edit value for `OptionList`
![preview](https://raw.githubusercontent.com/Smootheez/scl/refs/heads/dev/assets/image/edit_list_screen.png)
### Preview of add value for `OptionList`
![preview](https://raw.githubusercontent.com/Smootheez/scl/refs/heads/dev/assets/image/add_value_screen.png)
### Preview of confirmation screen
![preview](https://raw.githubusercontent.com/Smootheez/scl/refs/heads/dev/assets/image/confirmation_screen.png)
## Getting Started
### Step #1 Add Repository
Add the JitPack repository, include this in your `build.gradle`
``` groovy
repositories {
    maven {
        url "https://jitpack.io"
    }
}
```

### Step #2 Add Dependency
Implement this as mod dependencies
```groovy
dependencies {
    modImplementation "com.github.smootheez:scl:${project.scl_version}"
}
```
You can change `project.scl_version` in `gradle.properties`
```
scl_version = [version]
```
or you can put the version directly
```groovy
dependencies {
    modImplementation "com.github.smootheez:scl:[version]"
}
```

## Easy to use

### Step 1

```java
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
```

### Step 2
```json
    "entrypoints": {
        // ... existing entrypoints
		"scl": [
			"dev.smootheez.scl.example.ExampleConfig"
		]
	},
```

Just like that and let the magic happen!<br>
It will create the config file and the config screen<br>
_Note:_<br>
The config screen only generated if mod menu installed, and you declare `gui = true` also your config name and your mod id matches. `name = "your_modid"` otherwise it won't generate the config screen.

you can find latest and all version [here](https://github.com/Smootheez/scl/releases).

[![](https://jitpack.io/v/Smootheez/scl.svg)](https://jitpack.io/#Smootheez/scl)
## Important Notes
- Compatible with Minecraft 1.20.1+
- Licensed under MIT License

## Donation

If you find my work useful and want to support me, consider donate me on:

[![ko-fi](https://raw.githubusercontent.com/Smootheez/Smootheez/7b16ed55570e49b9320e9cade5e572b271e9f1fe/assets/donation-kofi.svg)](https://ko-fi.com/smootheez)
[![paypal](https://raw.githubusercontent.com/Smootheez/Smootheez/7b16ed55570e49b9320e9cade5e572b271e9f1fe/assets/donation-paypal.svg)](https://paypal.me/smootheez)