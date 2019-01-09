package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

public interface ExecuteModifier {
    @NotNull
    SubCommandResult getSubCommand(ExecutionContext execContext);

    boolean isIdempotent();

    boolean isSignificant();

    boolean isAbsolute();

    default Entity getNewSender() {
        return null;
    }

    //TODO: Add an ExecutionContext argument to getUsedExecutionVariables()
    default ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    default ExecutionVariableMap getModifiedExecutionVariables() {
        return null;
    }
}
