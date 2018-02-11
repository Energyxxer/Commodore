package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class ExecuteFacingBlock implements ExecuteModifier {
    private final CoordinateSet pos;

    public ExecuteFacingBlock(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, "facing " + pos.getAs(Coordinate.DisplayMode.ENTITY_POS));
    }

    @Override
    public boolean isIdempotent() {
        return pos.isIdempotent();
    }

    @Override
    public boolean isSignificant() {
        return pos.isSignificant();
    }

    @Override
    public boolean isAbsolute() {
        return pos.isAbsolute();
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return pos.getUsedExecutionVariables();
    }

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.YAW, ExecutionVariable.PITCH);
    }
}
