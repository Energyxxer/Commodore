package com.energyxxer.commodore.types;

public class DimensionType extends Type {
    public DimensionType(String name) {
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
