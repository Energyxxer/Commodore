package com.energyxxer.commodore.types;

public class DimensionType extends Type {
    protected DimensionType(String name) {
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
