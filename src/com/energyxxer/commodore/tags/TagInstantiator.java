package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;
import org.jetbrains.annotations.NotNull;

public interface TagInstantiator<T extends Tag> {
    @NotNull
    T instantiate(@NotNull TagGroup group, @NotNull Namespace namespace, @NotNull String name);
}
