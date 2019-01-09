package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class TagLongArray extends ArrayNBTTag<TagLong> {

    public TagLongArray() {
        this("");
    }

    public TagLongArray(@NotNull String name) {
        super(name, TagLong.class);
    }

    public TagLongArray(@NotNull String name, @NotNull Collection<@NotNull TagLong> content) {
        super(name, TagLong.class, content);
    }

    public TagLongArray(@NotNull String name, long... content) {
        super(name, TagLong.class);
        for(long i : content) {
            add(new TagLong(i));
        }
    }

    @Override
    public char getArrayPrefix() {
        return 'L';
    }

    @Override
    public @NotNull String getType() {
        return "TAG_Long_Array";
    }

    @Override
    public @NotNull NBTTag clone() {
        TagLongArray copy = new TagLongArray(name);
        this.content.forEach(t -> copy.add(t.clone()));
        return copy;
    }
}
