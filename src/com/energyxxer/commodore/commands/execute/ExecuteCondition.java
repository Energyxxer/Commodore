package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public abstract class ExecuteCondition implements ExecuteModifier {
    public enum ConditionType {
        IF, UNLESS
    }

    protected ConditionType type;

    public ExecuteCondition(ConditionType type) {
        this.type = type;
    }

    protected String getStarter() {
        return type.toString().toLowerCase() + " ";
    }

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.CONDITION);
    }

    //Rest of the condition types built by subclasses
}
