package com.energyxxer.commodore.versioning;

public interface Version {
    default boolean isComparableWith(Version other) {
        return this.getClass() == other.getClass();
    }

    int compare(Version other);
}
