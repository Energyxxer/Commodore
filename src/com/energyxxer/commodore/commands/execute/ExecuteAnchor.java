package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class ExecuteAnchor implements ExecuteModifier {

    public EntityAnchor anchor;

    public ExecuteAnchor(EntityAnchor anchor) {
        this.anchor = anchor;
    }

    @Override
    public String getSubCommand(Entity sender) {
        return "anchored " + anchor.toString().toLowerCase();
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
