package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class FluidType extends Type {
    protected FluidType(Namespace namespace, String id) {
        super(namespace, id);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
