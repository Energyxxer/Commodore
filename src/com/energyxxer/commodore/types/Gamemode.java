package com.energyxxer.commodore.types;

public class Gamemode extends Type {
    public Gamemode(String name) {
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
