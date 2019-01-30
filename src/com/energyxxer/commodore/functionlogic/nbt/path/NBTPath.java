package com.energyxxer.commodore.functionlogic.nbt.path;

import com.energyxxer.commodore.CommodoreException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class NBTPath implements Iterable<NBTPath> {
    @NotNull
    private final NBTPathNode node;
    @Nullable
    private final NBTPath next;

    public NBTPath(@NotNull String key) {
        this(key, null);
    }

    public NBTPath(@NotNull String key, @Nullable NBTPath next) {
        this(new NBTPathKey(key), next);
    }

    public NBTPath(int index) {
        this(index, null);
    }

    public NBTPath(int index, @Nullable NBTPath next) {
        this(new NBTPathIndex(index), next);
    }

    protected NBTPath(@NotNull NBTPathNode node, @Nullable NBTPath next) {
        this.node = node;
        this.next = next;
    }

    public NBTPath(@NotNull NBTPathNode... nodes) {
        if(nodes.length >= 1) {
            this.node = nodes[0];
            if(nodes.length > 1) {
                this.next = new NBTPath(Arrays.copyOfRange(nodes, 1, nodes.length));
            } else this.next = null;
        } else throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "Received empty array of path nodes", nodes);
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

    @NotNull
    public NBTPathNode getNode() {
        return node;
    }

    @Nullable
    public NBTPath getNext() {
        return next;
    }

    public boolean hasNext() {
        return next != null;
    }

    @NotNull
    @Override
    public Iterator<NBTPath> iterator() {
        return new NBTPathTraverser(this);
    }
}

