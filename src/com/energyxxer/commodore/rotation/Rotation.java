package com.energyxxer.commodore.rotation;

import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class Rotation implements ExecuteModifier {

    private RotationUnit yaw; //y-rot
    private RotationUnit pitch; //x-rot

    public Rotation(double yaw, double pitch) {
        this(yaw, pitch, RotationUnit.Type.ABSOLUTE);
    }

    public Rotation(double yaw, double pitch, RotationUnit.Type type) {
        this.yaw = new RotationUnit(type, yaw);
        this.pitch = new RotationUnit(type, pitch);
    }

    public Rotation(RotationUnit yaw, RotationUnit pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public String toString() {
        return yaw + " " + pitch;
    }

    @Override
    public String getSubCommand(Entity sender) {
        return "rotated " + yaw + " " + pitch;
    }

    @Override
    public boolean isIdempotent() {
        return yaw.isIdempotent() && pitch.isIdempotent();
    }

    @Override
    public boolean isSignificant() {
        return yaw.isSignificant() || pitch.isSignificant();
    }

    @Override
    public boolean isAbsolute() {
        return yaw.isAbsolute() && pitch.isAbsolute();
    }

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        ExecutionVariableMap map = new ExecutionVariableMap();
        if(yaw.isSignificant()) map.setUsed(ExecutionVariable.YAW);
        if(pitch.isSignificant()) map.setUsed(ExecutionVariable.PITCH);
        return map;
    }
}
