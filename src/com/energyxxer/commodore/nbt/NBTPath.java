package com.energyxxer.commodore.nbt;

import java.util.Objects;

public class NBTPath {

    private final NBTPathNode node;
    private final NBTPath next;

    public NBTPath(String key) {
        this(key, null);
    }

    public NBTPath(String key, NBTPath next) {
        this(new NBTPathKey(key), next);
    }

    public NBTPath(int index) {
        this(index, null);
    }

    public NBTPath(int index, NBTPath next) {
        this(new NBTPathIndex(index), next);
    }

    protected NBTPath(NBTPathNode node, NBTPath next) {
        this.node = node;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(node.getPathString());
        if(next != null) {
            sb.append(next.node.getPathSeparator());
            sb.append(next.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NBTPath nbtPath = (NBTPath) o;
        return Objects.equals(node, nbtPath.node) &&
                Objects.equals(next, nbtPath.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, next);
    }
}

interface NBTPathNode {
    String getPathString();
    String getPathSeparator();
}

class NBTPathKey implements NBTPathNode {
    private final String name;

    public NBTPathKey(String name) {
        this.name = name;
    }

    @Override
    public String getPathString() {
        return name;
    }

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

class NBTPathIndex implements NBTPathNode {
    private final int index;

    public NBTPathIndex(int index) {
        this.index = index;
    }

    @Override
    public String getPathString() {
        return "["+index+"]";
    }

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