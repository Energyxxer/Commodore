package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class EntityType extends Type {
    protected EntityType(Namespace namespace, String id) {
        super(namespace, id);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
