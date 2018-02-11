package com.energyxxer.commodore.coordinates;

import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.commands.execute.SubCommandResult;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class CoordinateSet implements ExecuteModifier {

    private final Coordinate x;
    private final Coordinate y;
    private final Coordinate z;

    public CoordinateSet(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public CoordinateSet(double x, double y, double z) {
        this(new Coordinate(x), new Coordinate(y), new Coordinate(z));
    }

    public CoordinateSet(double x, double y, double z, Coordinate.Type type) {
        this(new Coordinate(type, x), new Coordinate(type, y), new Coordinate(type, z));
    }

    public String getAs(Coordinate.DisplayMode mode) {
        return x.getAs(mode) + " " + y.getAs(mode) + " " + z.getAs(mode);
    }

    public String getXZAs(Coordinate.DisplayMode mode) {
        return x.getAs(mode) + " " + z.getAs(mode);
    }

    public Coordinate getX() {
        return x;
    }

    public Coordinate getY() {
        return y;
    }

    public Coordinate getZ() {
        return z;
    }

    @Override
    public String toString() {
        return getAs(Coordinate.DisplayMode.ENTITY_POS);
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, "positioned " + this.getAs(Coordinate.DisplayMode.ENTITY_POS));
    }

    @Override
    public boolean isIdempotent() {
        return x.isIdempotent() && y.isIdempotent() && z.isIdempotent();
    }

    @Override
    public boolean isSignificant() {
        return x.isSignificant() || y.isSignificant() || z.isSignificant();
    }

    @Override
    public boolean isAbsolute() {
        return x.getType() == Coordinate.Type.ABSOLUTE &&
                y.getType() == Coordinate.Type.ABSOLUTE &&
                z.getType() == Coordinate.Type.ABSOLUTE;
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        ExecutionVariableMap map = new ExecutionVariableMap(ExecutionVariable.DIMENSION);
        if(x.getType() != Coordinate.Type.ABSOLUTE) map.setUsed(ExecutionVariable.X);
        if(y.getType() != Coordinate.Type.ABSOLUTE) map.setUsed(ExecutionVariable.Y);
        if(z.getType() != Coordinate.Type.ABSOLUTE) map.setUsed(ExecutionVariable.Z);
        return map;
    }

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        ExecutionVariableMap map = new ExecutionVariableMap();
        if(x.isSignificant()) map.setUsed(ExecutionVariable.X);
        if(y.isSignificant()) map.setUsed(ExecutionVariable.Y);
        if(z.isSignificant()) map.setUsed(ExecutionVariable.Z);
        return map;
    }
}
