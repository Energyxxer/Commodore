package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class BiomeType extends Type {
    protected BiomeType(Namespace namespace, String id) {
        super(namespace, id);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
