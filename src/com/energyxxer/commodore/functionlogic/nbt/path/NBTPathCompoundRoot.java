package com.energyxxer.commodore.functionlogic.nbt.path;

import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import org.jetbrains.annotations.NotNull;

public class NBTPathCompoundRoot implements NBTPathNode {
    @NotNull
    private final TagCompound compoundMatch;

    public NBTPathCompoundRoot(@NotNull TagCompound objectMatch) {
        this.compoundMatch = objectMatch;
    }

    @NotNull
    @Override
    public String getPathString() {
        return compoundMatch.toHeadlessString();
    }

    @NotNull
    @Override
    public String getPathSeparator() {
        return "";
    }
}
