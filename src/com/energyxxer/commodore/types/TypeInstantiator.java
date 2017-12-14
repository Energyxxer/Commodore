package com.energyxxer.commodore.types;

public interface TypeInstantiator<T extends Type> {
    T create(String name);
}
