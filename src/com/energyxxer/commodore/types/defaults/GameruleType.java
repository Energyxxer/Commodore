package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.types.Type;

public class GameruleType extends Type {
    public static final String CATEGORY = "gamerule";

    public GameruleType(String name) {
        super(CATEGORY, null, name);
    }

    @Override
    public boolean useNamespace() {
        return false;
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
