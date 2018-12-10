package com.energyxxer.commodore.types;

import org.jetbrains.annotations.NotNull;

public interface TypeInstantiator<T extends Type> {
    @NotNull
    T create(@NotNull String name);
}
