package dev.smootheez.scl.api;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {
    /**
    * The name of the config
    */
    String name();
    /**
    * If the config should have a gui
    * Note: Make sure the config name is the same as the mod id
    */
    boolean gui() default false;
}
