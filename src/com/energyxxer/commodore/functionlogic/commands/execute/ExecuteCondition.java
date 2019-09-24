package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public abstract class ExecuteCondition implements ExecuteModifier {
    public enum ConditionType {
        IF, UNLESS
    }

    @NotNull
    protected final ConditionType type;

    public ExecuteCondition(@NotNull ConditionType type) {
        this.type = type;
    }

    @NotNull
    protected String getStarter() {
        return type.toString().toLowerCase(Locale.ENGLISH) + " ";
    }

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.CONDITION);
    }

    @NotNull
    public ConditionType getType() {
        return type;
    }

    //Rest of the condition types built by subclasses
}
