package com.energyxxer.commodore.commands.chunk;

import com.energyxxer.commodore.coordinates.CoordinateSet;

public class ForceLoadRemoveCommand extends ForceLoadChangeCommand {
    public ForceLoadRemoveCommand(CoordinateSet from) {
        this(from, null);
    }

    public ForceLoadRemoveCommand(CoordinateSet from, CoordinateSet to) {
        super("remove", from, to);
    }
}
