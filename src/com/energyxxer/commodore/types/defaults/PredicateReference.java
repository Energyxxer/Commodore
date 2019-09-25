package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;

public class PredicateReference extends Type {
    public static final String CATEGORY = "predicate_reference";

    public PredicateReference(Namespace namespace, String name) {
        super(CATEGORY, namespace, name);
    }

    @Override
    public boolean isStandalone() {
        return true;
    }
}
