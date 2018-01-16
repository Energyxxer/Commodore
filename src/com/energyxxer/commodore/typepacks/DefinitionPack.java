package com.energyxxer.commodore.typepacks;

import com.energyxxer.commodore.module.CommandModule;

public abstract class DefinitionPack {

    private String name;
    private DefinitionPackInitializer initializer;

    public DefinitionPack(String name) {
        this.name = name;
        this.initializer = initializer;
    }

    public abstract void initialize(CommandModule module);

    @Override
    public String toString() {
        return "DefinitionPack [" + name + "]";
    }
}
