package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public interface ExecuteModifier {
    SubCommandResult getSubCommand(ExecutionContext execContext);

    @NotNull
    default Collection<ScoreboardAccess> getScoreboardAccesses() {
        return Collections.emptyList();
    }

    boolean isIdempotent();

    boolean isSignificant();

    boolean isAbsolute();

    default Entity getNewSender() {
        return null;
    }

    //TODO: Add an Entity sender argument to getUsedExecutionVariables()
    default ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    default ExecutionVariableMap getModifiedExecutionVariables() {
        return null;
    }
}
