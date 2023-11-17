package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class ComplexNBTTag extends NBTTag {

    public ComplexNBTTag(@NotNull String name) {
        super(name);
    }

    public void addAll(@NotNull Collection<@NotNull NBTTag> tags) {
        tags.forEach(this::add);
    }

    public abstract void add(@NotNull NBTTag tag);

    public abstract void ensureCapacity(int minCapacity);

    public abstract int size();

    public abstract boolean isEmpty();

    public abstract boolean contains(@NotNull String key);

    public abstract List<@NotNull NBTTag> getAllTags();

    public abstract Iterator<? extends NBTTag> iterator();
}
