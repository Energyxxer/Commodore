package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

import static com.energyxxer.commodore.commands.execute.SubCommandResult.ExecutionChange.PITCH;
import static com.energyxxer.commodore.commands.execute.SubCommandResult.ExecutionChange.YAW;

public class ExecuteFacingBlock implements ExecuteModifier {
    private CoordinateSet pos;

    public ExecuteFacingBlock(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult("facing " + pos.getAs(Coordinate.DisplayMode.ENTITY_POS), YAW, PITCH);
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
    public ExecutionVariableMap getModifiedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.YAW, ExecutionVariable.PITCH);
    }
}
