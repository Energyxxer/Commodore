package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class ExecuteAlignment implements ExecuteModifier {
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;

    public ExecuteAlignment(boolean x, boolean y, boolean z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String getSubCommand(Entity sender) {
        return "align " + ((x) ? "x" : "") + ((y) ? "y" : "") + ((z) ? "z" : "");
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return x || y || z;
    }

    @Override
    public boolean isAbsolute() {
        return false;
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return getModifiedExecutionVariables();
    }

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        ExecutionVariableMap map = new ExecutionVariableMap();
        if(x) map.setUsed(ExecutionVariable.X);
        if(y) map.setUsed(ExecutionVariable.Y);
        if(z) map.setUsed(ExecutionVariable.Z);
        return map;
    }
}
