package com.energyxxer.commodore.functionlogic.commands.chunk;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForceLoadRemoveCommand extends ForceLoadChangeCommand {
    public ForceLoadRemoveCommand(@NotNull CoordinateSet from) {
        this(from, null);
    }

    public ForceLoadRemoveCommand(@NotNull CoordinateSet from, @Nullable CoordinateSet to) {
        super("remove", from, to);
    }
}
