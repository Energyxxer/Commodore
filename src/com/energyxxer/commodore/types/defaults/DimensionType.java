package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;

public class DimensionType extends Type {
    public static final String CATEGORY = "dimension";

    protected DimensionType(Namespace namespace, String id) {
        super(CATEGORY, namespace, id);
    }

    @Override
    public boolean useNamespace() {
        return true;
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
