package com.energyxxer.commodore.nbt.path;

import java.util.Iterator;

public class NBTPathTraverser implements Iterator<NBTPath>  {
    private NBTPath path;

    public NBTPathTraverser(NBTPath path) {
        this.path = path;
    }

    @Override
    public boolean hasNext() {
        return path != null;
    }

    @Override
    public NBTPath next() {
        if(!hasNext()) return null;
        NBTPath toReturn = path;
        path = path.getNext();
        return toReturn;
    }
}
