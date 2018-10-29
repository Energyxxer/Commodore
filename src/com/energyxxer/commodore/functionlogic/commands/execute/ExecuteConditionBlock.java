package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.Type;

import static com.energyxxer.commodore.types.TypeAssert.assertBlock;

public class ExecuteConditionBlock extends ExecuteCondition {
    private final CoordinateSet pos;
    private final Type blockType;

    public ExecuteConditionBlock(ConditionType flowController, CoordinateSet pos, Type blockType) {
        super(flowController);
        assertBlock(blockType);
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
