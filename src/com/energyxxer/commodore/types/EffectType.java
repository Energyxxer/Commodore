package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class EffectType extends Type {
    protected EffectType(Namespace namespace, String id) {
        super(namespace, id);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
