package com.energyxxer.commodore.functionlogic.nbt.path;

import org.jetbrains.annotations.NotNull;

public interface NBTPathNode {
    @NotNull
    String getPathString();
    @NotNull
    String getPathSeparator();
}
