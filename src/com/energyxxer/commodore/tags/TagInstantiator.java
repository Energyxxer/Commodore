package com.energyxxer.commodore.tags;

public interface TagInstantiator<T extends Tag> {
    T instantiate(String namespace, String name);
}
