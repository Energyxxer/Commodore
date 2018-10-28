package com.energyxxer.commodore.commands.chunk;

import com.energyxxer.commodore.coordinates.CoordinateSet;

public class ForceLoadAddCommand extends ForceLoadChangeCommand {
    public ForceLoadAddCommand(CoordinateSet from) {
        this(from, null);
    }

    public ForceLoadAddCommand(CoordinateSet from, CoordinateSet to) {
        super("add", from, to);
    }
}
