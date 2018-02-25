package com.energyxxer.commodore.nbt;

import java.util.Collection;

public abstract class ComplexNBTTag extends NBTTag {

    public ComplexNBTTag(String name) {
        super(name);
    }

    public void addAll(Collection<NBTTag> tags) {
        tags.forEach(this::add);
    }

    public abstract void add(NBTTag tag);
}
