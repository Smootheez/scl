# Smootheez Config Lib (SCL)
A utility mod designed to simplify configuration handling for Minecraft modding.
Primarily developed for my own mods, but available for public use.
## What is SCL?
SCL provides an easier way to manage configurations for Minecraft mods.
It handles the boilerplate code and provides a clean interface for:
- Config file management
- Automatic config screen generation
- Automatic configuration generation

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
scl_version = version
```
or you can put the version directly
```groovy
dependencies {
    modImplementation "com.github.smootheez:scl:version"
}
```
[![](https://jitpack.io/v/Smootheez/scl.svg)](https://jitpack.io/#Smootheez/scl)
## Important Notes
- Compatible with Minecraft 1.20.1+
- Licensed under MIT License