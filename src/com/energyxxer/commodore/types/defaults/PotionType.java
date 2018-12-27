package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class PotionType extends Type {
    public static final String CATEGORY = "potion";

    public PotionType(Namespace namespace, @NotNull String id) {
        super(CATEGORY, namespace, id);
    }

    @Override
    public boolean isStandalone() {
        return true;
    }
}
