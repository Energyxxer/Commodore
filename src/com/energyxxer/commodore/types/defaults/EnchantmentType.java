package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;

public class EnchantmentType extends Type {
    public static final String CATEGORY = "enchantment";

    protected EnchantmentType(Namespace namespace, String id) {
        super(CATEGORY, namespace, id);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
