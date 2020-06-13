package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;

public class FontReference extends Type {
    public static final String CATEGORY = "font";

    public FontReference(Namespace namespace, String path) {
        super(CATEGORY, namespace, path);
    }

    @Override
    public boolean isStandalone() {
        return true;
    }
}
