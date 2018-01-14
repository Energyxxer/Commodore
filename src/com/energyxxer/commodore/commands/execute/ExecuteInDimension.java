package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.DimensionType;

public class ExecuteInDimension implements ExecuteModifier {
    private DimensionType dimension;

    public ExecuteInDimension(DimensionType dimension) {
        this.dimension = dimension;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult("in " + dimension);
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return true;
    }

    @Override
    public boolean isAbsolute() {
        return true;
    }
}
