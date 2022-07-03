package com.energyxxer.commodore.module;

import com.energyxxer.commodore.tags.TagManager;
import com.energyxxer.commodore.types.defaults.TypeManager;

public interface DefinitionPopulatable {
    TypeManager getTypeManager(String namespace);
    TagManager getTagManager(String namespace);
    void putResource(String key, Object value);
    void createCategoryAlias(String from, String to);
}
