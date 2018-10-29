package com.energyxxer.commodore.functionlogic.commands.chunk;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

public class ForceLoadAddCommand extends ForceLoadChangeCommand {
    public ForceLoadAddCommand(CoordinateSet from) {
        this(from, null);
    }

    public ForceLoadAddCommand(CoordinateSet from, CoordinateSet to) {
        super("add", from, to);
    }
}
