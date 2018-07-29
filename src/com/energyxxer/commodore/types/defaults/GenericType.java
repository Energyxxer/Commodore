package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.TypeDictionary;

/**
 * Defines a type not bound by predefined categories. These can be of any category, and are created
 * by custom dictionaries in type managers.
 *
 * @see Type
 * @see TypeDictionary
 * @see TypeManager
 * @see TypeManager#createDictionary(String, boolean)
 * @see TypeDictionary#create(String)
 * */
public class GenericType extends Type {
    GenericType(String category, Namespace namespace, String name) {
        super(category, namespace, name);
    }

    @Override
    public boolean useNamespace() {
        return namespace != null;
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
