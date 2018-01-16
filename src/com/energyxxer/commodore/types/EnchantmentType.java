package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public class EnchantmentType extends Type {
    protected EnchantmentType(Namespace namespace, String id) {
        super(namespace, id);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
