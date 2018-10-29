package com.energyxxer.commodore.functionlogic.nbt.path;

import com.energyxxer.commodore.functionlogic.nbt.TagCompound;

public class NBTObjectMatch implements NBTPathNode {
    private final TagCompound objectMatch;

    public NBTObjectMatch() {
        this(null);
    }

    public NBTObjectMatch(TagCompound objectMatch) {
        this.objectMatch = objectMatch;
    }

    @Override
    public String getPathString() {
        return objectMatch != null ? objectMatch.toHeadlessString() : "";
    }

    @Override
    public String getPathSeparator() {
        return "";
    }
}
