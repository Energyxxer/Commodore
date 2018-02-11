package com.energyxxer.commodore.types;

public class StructureType extends Type {
    public StructureType(String name) {
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
