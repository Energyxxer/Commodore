package com.energyxxer.commodore.functionlogic.nbt.path;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NBTPathKey implements NBTPathNode {
    @NotNull
    private final String name;

    public NBTPathKey(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String getPathString() {
        return name;
    }

    @NotNull
    @Override
    public String getPathSeparator() {
        return ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NBTPathKey that = (NBTPathKey) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
