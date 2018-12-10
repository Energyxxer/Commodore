package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public abstract class ComplexNBTTag extends NBTTag {

    public ComplexNBTTag(@NotNull String name) {
        super(name);
    }

    public void addAll(@NotNull Collection<@NotNull NBTTag> tags) {
        tags.forEach(this::add);
    }

    public abstract void add(@NotNull NBTTag tag);

    public abstract int size();

    public abstract boolean isEmpty();

    public abstract boolean contains(@NotNull String key);

    @NotNull
    public abstract Collection<@NotNull NBTTag> getAllTags();
}
