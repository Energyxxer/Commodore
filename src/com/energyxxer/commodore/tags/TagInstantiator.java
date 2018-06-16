package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;

public interface TagInstantiator<T extends Tag> {
    T instantiate(TagGroup group, Namespace namespace, String name);
}
