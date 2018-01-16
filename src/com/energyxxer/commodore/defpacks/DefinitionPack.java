package com.energyxxer.commodore.defpacks;

import com.energyxxer.commodore.module.CommandModule;

public class DefinitionPack {

    private String name;
    private DefinitionPackInitializer initializer;

    public DefinitionPack(String name) {
        this.name = name;
        this.initializer = initializer;
    }

    public void initialize(CommandModule module) {

    }

    @Override
    public String toString() {
        return "DefinitionPack [" + name + "]";
    }
}
