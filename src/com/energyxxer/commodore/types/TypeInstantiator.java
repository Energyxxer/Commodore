package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TypeInstantiator<T extends Type> {
    @NotNull
    T create(@Nullable Namespace namespace, @NotNull String name);
}
