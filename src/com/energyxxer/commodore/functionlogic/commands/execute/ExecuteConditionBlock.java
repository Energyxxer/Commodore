package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.Type;

public class ExecuteConditionBlock extends ExecuteCondition {
    private final CoordinateSet pos;
    private final Block block;

    public ExecuteConditionBlock(ConditionType flowController, CoordinateSet pos, Type blockType) {
        this(flowController, pos, new Block(blockType));
    }

    public ExecuteConditionBlock(ConditionType flowController, CoordinateSet pos, Block block) {
        super(flowController);
        this.pos = pos;
        this.block = block;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + block);
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
