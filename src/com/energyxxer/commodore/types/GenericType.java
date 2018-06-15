package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class GenericType extends Type {
    public GenericType(String category, Namespace namespace, String name) {
        super(category, namespace, name);
    }

    @Override
    public boolean useNamespace() {
        return namespace != null;
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
