package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.types.Type;

public class ItemSlot extends Type {
    public static final String CATEGORY = "slot";

    public ItemSlot(String name) {
        super(CATEGORY, null, name);
    }

    @Override
    public boolean useNamespace() {
        return false;
    }

    @Override
    public boolean isStandalone() {
        return true;
    }
}
