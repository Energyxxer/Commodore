package com.energyxxer.commodore.versioning;

import org.jetbrains.annotations.NotNull;

public interface Version {
    default boolean isComparableWith(Version other) {
        return this.getClass() == other.getClass();
    }

    int compare(Version other);

    @NotNull
    String getEditionString();
    @NotNull
    String getVersionString();
}
