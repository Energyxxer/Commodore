package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class TagByteArray extends ArrayNBTTag<TagByte> {

    public TagByteArray(@NotNull String name) {
        super(name, TagByte.class);
    }

    public TagByteArray(@NotNull String name, @NotNull Collection<@NotNull TagByte> content) {
        super(name, TagByte.class, content);
    }

    public TagByteArray(@NotNull String name, byte... content) {
        super(name, TagByte.class);
        for(byte i : content) {
            add(new TagByte(i));
        }
    }

    public TagByteArray(@NotNull String name, int... content) {
        super(name, TagByte.class);
        for(int i : content) {
            add(new TagByte(i));
        }
    }

    @Override
    public char getArrayPrefix() {
        return 'B';
    }

    @Override
    public @NotNull String getType() {
        return "TAG_Byte_Array";
    }

    @Override
    public @NotNull NBTTag clone() {
        TagByteArray copy = new TagByteArray(name);
        this.content.forEach(t -> copy.add(t.clone()));
        return copy;
    }
}
