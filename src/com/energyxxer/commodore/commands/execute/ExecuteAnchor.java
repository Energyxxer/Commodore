package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class ExecuteAnchor implements ExecuteModifier {

    public final EntityAnchor anchor;

    public ExecuteAnchor(EntityAnchor anchor) {
        this.anchor = anchor;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, "anchored " + anchor.toString().toLowerCase());
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
        return true;
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.SENDER);
    }

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.ANCHOR);
    }
}
