package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class ArrayNBTTag<T extends NBTTag> extends ComplexNBTTag {
    @NotNull
    protected final ArrayList<T> content = new ArrayList<>();
    private final Class<T> tagClass;

    public ArrayNBTTag(@NotNull String name, @NotNull Class<T> cls) {
        super(name);
        tagClass = cls;
    }

    public ArrayNBTTag(@NotNull String name, @NotNull Class<T> cls, @NotNull Collection<@NotNull T> content) {
        this(name, cls);
        content.forEach(this::add);
    }

    @Override
    public void add(@NotNull NBTTag tag) {
        if(tagClass.isInstance(tag)) {
            content.add((T) tag);
        } else {
            throw new IllegalArgumentException("Unable to add tag of type " + tag.getType() + " to array of type " + getType() + "; Tag: " + tag);
        }
    }

    @Override
    public int size() {
        return content.size();
    }

    @Override
    public boolean isEmpty() {
        return content.isEmpty();
    }

    @Override
    public boolean contains(@NotNull String key) {
        return false;
    }

    @Override
    public List<@NotNull NBTTag> getAllTags() {
        return new ArrayList<>(content);
    }

    public abstract char getArrayPrefix();

    @Override
    public @NotNull String toHeadlessString() {
        StringBuilder sb = new StringBuilder("[");

        sb.append(getArrayPrefix());
        sb.append(';');

        Iterator<T> it = content.iterator();
        while(it.hasNext()) {
            NBTTag tag = it.next();
            sb.append(tag.toHeadlessString());
            if(it.hasNext()) sb.append(',');
        }
        sb.append(']');

        return sb.toString();
    }
}
