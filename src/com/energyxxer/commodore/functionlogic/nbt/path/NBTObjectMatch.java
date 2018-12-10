package com.energyxxer.commodore.functionlogic.nbt.path;

import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import org.jetbrains.annotations.NotNull;

public class NBTObjectMatch implements NBTPathNode {
    @NotNull
    private final TagCompound objectMatch;

    public NBTObjectMatch(@NotNull TagCompound objectMatch) {
        this.objectMatch = objectMatch;
    }

    @NotNull
    @Override
    public String getPathString() {
        return objectMatch.toHeadlessString();
    }

    @NotNull
    @Override
    public String getPathSeparator() {
        return "";
    }
}
