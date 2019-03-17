package com.energyxxer.commodore.functionlogic.nbt.path;

import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import org.jetbrains.annotations.NotNull;

/**
 * Deprecated. Instead use the {@link NBTPathKey} node with the optional TagCompound,
 * as this node class in its current implementation may lead to invalid NBT Paths.
 * Or use {@link NBTPathCompoundRoot} for object matches in the root compound.
 * */
@Deprecated
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
