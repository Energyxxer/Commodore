package com.energyxxer.commodore.module.options;

/**
 * Describes a customizable option for a command module.<br>
 *
 * @param <T> The type of the value accepted by the option.
 * */
public class ModuleOption<T> {
    private final String name;
    private T value;

    public ModuleOption(String name, T defaultValue) {
        this.name = name;
        this.value = defaultValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ModuleOption [" + name + ":" + value + "]";
    }
}
