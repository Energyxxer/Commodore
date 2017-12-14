package com.energyxxer.commodore.types;

public class Difficulty extends Type {
    public Difficulty(String name) {
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
