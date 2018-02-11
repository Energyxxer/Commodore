package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class BossbarReference extends Type {

    public BossbarReference(Namespace namespace, String name) {
        super(namespace, name);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
