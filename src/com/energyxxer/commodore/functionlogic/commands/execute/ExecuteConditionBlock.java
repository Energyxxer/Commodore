package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.block.BlockFormatter;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class ExecuteConditionBlock extends ExecuteCondition {
    @NotNull
    private final CoordinateSet pos;
    @NotNull
    private final Block block;

    public ExecuteConditionBlock(@NotNull ConditionType flowController, @NotNull CoordinateSet pos, @NotNull Type blockType) {
        this(flowController, pos, new Block(blockType));
    }

    public ExecuteConditionBlock(@NotNull ConditionType flowController, @NotNull CoordinateSet pos, @NotNull Block block) {
        super(flowController);
        this.pos = pos;
        this.block = block;
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        if(!VersionFeatureManager.getBoolean("execute_modifiers", false)) {
            return new SubCommandResult(execContext, "detect " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + BlockFormatter.asMatch(block));
        } else {
            return new SubCommandResult(execContext, this.getStarter() + "block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + BlockFormatter.asMatch(block));
        }
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

    @NotNull
    public CoordinateSet getPos() {
        return pos;
    }

    @NotNull
    public Block getBlock() {
        return block;
    }
}
