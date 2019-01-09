package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class TagIntArray extends ArrayNBTTag<TagInt> {

    public TagIntArray() {
        this("");
    }

    public TagIntArray(@NotNull String name) {
        super(name, TagInt.class);
    }

    public TagIntArray(@NotNull String name, @NotNull Collection<@NotNull TagInt> content) {
        super(name, TagInt.class, content);
    }

    public TagIntArray(@NotNull String name, int... content) {
        super(name, TagInt.class);
        for(int i : content) {
            add(new TagInt(i));
        }
    }

    @Override
    public char getArrayPrefix() {
        return 'I';
    }

    @Override
    public @NotNull String getType() {
        return "TAG_Int_Array";
    }

    @Override
    public @NotNull NBTTag clone() {
        TagIntArray copy = new TagIntArray(name);
        this.content.forEach(t -> copy.add(t.clone()));
        return copy;
    }
}
