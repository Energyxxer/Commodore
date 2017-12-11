package com.energyxxer.commodore.rotation;

public class Rotation {

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
}
