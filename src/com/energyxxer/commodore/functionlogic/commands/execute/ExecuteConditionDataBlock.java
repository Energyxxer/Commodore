package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class ExecuteConditionDataBlock extends ExecuteCondition {
    @NotNull
    private final CoordinateSet pos;
    @NotNull
    private final NBTPath path;

    public ExecuteConditionDataBlock(@NotNull ConditionType flowController, @NotNull CoordinateSet pos, @NotNull NBTPath path) {
        super(flowController);
        this.pos = pos;
        this.path = path;
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "data block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + path);
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
        return false;
    }
}
