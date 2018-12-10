package com.energyxxer.commodore.functionlogic.nbt.path;

import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class NBTPathTraverser implements Iterator<NBTPath>  {
    @Nullable
    private NBTPath path;

    public NBTPathTraverser(@Nullable NBTPath path) {
        this.path = path;
    }

    @Override
    public boolean hasNext() {
        return path != null;
    }

    @Override
    public NBTPath next() {
        if(path == null) return null;
        NBTPath toReturn = path;
        path = path.getNext();
        return toReturn;
    }
}
