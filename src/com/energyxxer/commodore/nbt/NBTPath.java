package com.energyxxer.commodore.nbt;

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
}