package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class ItemType extends Type {
    protected ItemType(Namespace namespace, String id) {
        super(namespace, id);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
