package com.energyxxer.commodore.functionlogic.commands.chunk;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForceLoadAddCommand extends ForceLoadChangeCommand {
    public ForceLoadAddCommand(@NotNull CoordinateSet from) {
        this(from, null);
    }

    public ForceLoadAddCommand(@NotNull CoordinateSet from, @Nullable CoordinateSet to) {
        super("add", from, to);
    }
}
