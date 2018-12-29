package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;
import org.jetbrains.annotations.NotNull;

public interface CustomTypeInstantiator {
    Type create(@NotNull String category, Namespace namespace, @NotNull String name);
}
