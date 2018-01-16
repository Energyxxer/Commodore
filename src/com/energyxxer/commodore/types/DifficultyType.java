package com.energyxxer.commodore.types;

public class DifficultyType extends Type {
    protected DifficultyType(String name) {
        super(null, name);
    }

    @Override
    public boolean useNamespace() {
        return false;
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
