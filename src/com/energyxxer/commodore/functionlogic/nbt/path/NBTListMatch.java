package com.energyxxer.commodore.functionlogic.nbt.path;

import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NBTListMatch implements NBTPathNode {
    @Nullable
    private final TagCompound listMatch;

    public NBTListMatch() {
        this(null);
    }

    public NBTListMatch(@Nullable TagCompound listMatch) {
        this.listMatch = listMatch;
    }

    @NotNull
    @Override
    public String getPathString() {
        return "[" + (listMatch != null ? listMatch.toHeadlessString() : "") + "]";
    }

    @NotNull
    @Override
    public String getPathSeparator() {
        return "";
    }
}
