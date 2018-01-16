package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class ParticleType extends Type {
    protected ParticleType(Namespace namespace, String id) {
        super(namespace, id);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
