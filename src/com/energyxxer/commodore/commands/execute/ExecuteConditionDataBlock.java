package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.nbt.path.NBTPath;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class ExecuteConditionDataBlock extends ExecuteCondition {
    private final CoordinateSet pos;
    private final NBTPath path;

    public ExecuteConditionDataBlock(ConditionType flowController, CoordinateSet pos, NBTPath path) {
        super(flowController);
        this.pos = pos;
        this.path = path;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "data block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + path);
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        return pos.getScoreboardAccesses();
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
