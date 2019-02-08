package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

public class ExecuteAlignment implements ExecuteModifier {
    private final boolean x;
    private final boolean y;
    private final boolean z;

    public ExecuteAlignment(boolean x, boolean y, boolean z) {
        if(!(x || y || z)) throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "At least one axis must be included in an execute align modifier");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, "align " + ((x) ? "x" : "") + ((y) ? "y" : "") + ((z) ? "z" : ""));
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

    public boolean isX() {
        return x;
    }

    public boolean isY() {
        return y;
    }

    public boolean isZ() {
        return z;
    }
}
