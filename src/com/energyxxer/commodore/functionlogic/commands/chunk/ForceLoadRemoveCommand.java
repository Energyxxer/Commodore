package com.energyxxer.commodore.functionlogic.commands.chunk;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

public class ForceLoadRemoveCommand extends ForceLoadChangeCommand {
    public ForceLoadRemoveCommand(CoordinateSet from) {
        this(from, null);
    }

    public ForceLoadRemoveCommand(CoordinateSet from, CoordinateSet to) {
        super("remove", from, to);
    }
}
