package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.BlockType;

public class ExecuteConditionBlock extends ExecuteCondition {
    public CoordinateSet pos;
    public BlockType blockType;

    public ExecuteConditionBlock(ConditionType flowController, CoordinateSet pos, BlockType blockType) {
        super(flowController);
        this.pos = pos;
        this.blockType = blockType;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + blockType);
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

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return pos.getUsedExecutionVariables();
    }
}
