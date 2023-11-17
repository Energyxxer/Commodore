package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

public class ExecutePositioned implements ExecuteModifier {

    @NotNull
    public CoordinateSet pos;

    public ExecutePositioned(@NotNull CoordinateSet pos) {
        this.pos = pos;
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, "positioned " + pos.getAs(Coordinate.DisplayMode.ENTITY_POS));
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
        ExecutionVariableMap map = new ExecutionVariableMap();
        if(pos.getX().isSignificant()) map.setUsed(ExecutionVariable.X);
        if(pos.getY().isSignificant()) map.setUsed(ExecutionVariable.Y);
        if(pos.getZ().isSignificant()) map.setUsed(ExecutionVariable.Z);
        return map;
    }
}
