package com.energyxxer.commodore.types;

public class GamemodeType extends Type {
    protected GamemodeType(String name) {
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
