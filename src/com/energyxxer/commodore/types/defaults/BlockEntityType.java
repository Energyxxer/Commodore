package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BlockEntityType extends Type {
    public static final String CATEGORY = "block_entity";

    public BlockEntityType(Namespace namespace, @NotNull String id) {
        super(CATEGORY, namespace, id);
    }

    @Override
    public boolean isStandalone() {
        return true;
    }
}
