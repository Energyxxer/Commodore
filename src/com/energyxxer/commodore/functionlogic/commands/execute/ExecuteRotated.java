package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.rotation.Rotation;
import org.jetbrains.annotations.NotNull;

public class ExecuteRotated implements ExecuteModifier {

    @NotNull
    public Rotation rot;

    public ExecuteRotated(@NotNull Rotation rot) {
        this.rot = rot;
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, "rotated " + rot.getYaw() + " " + rot.getPitch());
    }

    @Override
    public boolean isIdempotent() {
        return rot.isIdempotent();
    }

    @Override
    public boolean isSignificant() {
        return rot.isSignificant();
    }

    @Override
    public boolean isAbsolute() {
        return rot.isAbsolute();
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return rot.getUsedExecutionVariables();
    }

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        ExecutionVariableMap map = new ExecutionVariableMap();
        if(rot.getYaw().isSignificant()) map.setUsed(ExecutionVariable.YAW);
        if(rot.getPitch().isSignificant()) map.setUsed(ExecutionVariable.PITCH);
        return map;
    }
}
