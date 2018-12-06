package com.energyxxer.commodore.functionlogic.nbt;

import java.util.Collection;

public abstract class ComplexNBTTag extends NBTTag {

    public ComplexNBTTag(String name) {
        super(name);
    }

    public void addAll(Collection<NBTTag> tags) {
        tags.forEach(this::add);
    }

    public abstract void add(NBTTag tag);

    public abstract int size();

    public abstract boolean isEmpty();

    public abstract boolean contains(String key);

    public abstract Collection<NBTTag> getAllTags();
}
