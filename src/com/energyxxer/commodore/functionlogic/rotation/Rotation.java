package com.energyxxer.commodore.functionlogic.rotation;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Rotation {

    @NotNull
    private final RotationUnit yaw; //y-rot
    @NotNull
    private final RotationUnit pitch; //x-rot

    public Rotation(double yaw, double pitch) {
        this(yaw, pitch, RotationUnit.Type.ABSOLUTE);
    }

    public Rotation(double yaw, double pitch, @NotNull RotationUnit.Type type) {
        this.yaw = new RotationUnit(type, yaw);
        this.pitch = new RotationUnit(type, pitch);
    }

    public Rotation(@NotNull RotationUnit yaw, @NotNull RotationUnit pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public String toString() {
        return yaw + " " + pitch;
    }

    public boolean isIdempotent() {
        return yaw.isIdempotent() && pitch.isIdempotent();
    }

    public boolean isSignificant() {
        return yaw.isSignificant() || pitch.isSignificant();
    }

    public boolean isAbsolute() {
        return yaw.isAbsolute() && pitch.isAbsolute();
    }

    @NotNull
    public RotationUnit getYaw() {
        return yaw;
    }

    @NotNull
    public RotationUnit getPitch() {
        return pitch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rotation rotation = (Rotation) o;
        return Objects.equals(yaw, rotation.yaw) &&
                Objects.equals(pitch, rotation.pitch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yaw, pitch);
    }

    public ExecutionVariableMap getUsedExecutionVariables() {
        ExecutionVariableMap map = new ExecutionVariableMap();
        if(yaw.getType() != RotationUnit.Type.ABSOLUTE) map.setUsed(ExecutionVariable.YAW);
        if(pitch.getType() != RotationUnit.Type.ABSOLUTE) map.setUsed(ExecutionVariable.PITCH);
        return map;
    }
}
