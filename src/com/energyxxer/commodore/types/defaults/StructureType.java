package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.types.Type;

public class StructureType extends Type {
    public static final String CATEGORY = "structure";

    public StructureType(String name) {
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
