package com.energyxxer.commodore.functionlogic.nbt.path;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NBTPathIndex implements NBTPathNode {
    private final int index;

    public NBTPathIndex(int index) {
        this.index = index;
    }

    @NotNull
    @Override
    public String getPathString() {
        return "["+index+"]";
    }

    @NotNull
    @Override
    public String getPathSeparator() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NBTPathIndex that = (NBTPathIndex) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
