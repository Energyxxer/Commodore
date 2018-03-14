package com.energyxxer.commodore.types;

public class ItemSlot extends Type {
    public ItemSlot(String name) {
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
