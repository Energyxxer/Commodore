package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class DimensionType extends Type {
    protected DimensionType(Namespace namespace, String id) {
        super(namespace, id);
    }

    @Override
    public boolean useNamespace() {
        return true;
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
