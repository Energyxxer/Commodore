package com.energyxxer.commodore.types;

public class GameruleType extends Type {
    public GameruleType(String name) {
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
