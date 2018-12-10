package com.energyxxer.commodore.functionlogic.coordinates;

import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.functionlogic.commands.execute.SubCommandResult;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a set of three coordinates, for axes x, y and z.<br>
 *
 *     This doubles as an execute modifier class for the <code>execute positioned &lt;x&gt; &lt;y&gt; &lt;z&gt;</code>
 *     subcommand.
 *
 * @see Coordinate
 * @see ExecuteModifier
 * */
public class CoordinateSet implements ExecuteModifier {

    /**
     * The coordinate for this set's x axis.
     * */
    @NotNull
    private final Coordinate x;
    /**
     * The coordinate for this set's y axis.
     * */
    @NotNull
    private final Coordinate y;
    /**
     * The coordinate for this set's z axis.
     * */
    @NotNull
    private final Coordinate z;

    /**
     * Creates a coordinate set that points to the current execution position, that is, <code>~ ~ ~</code>.
     * */
    public CoordinateSet() {
        this(0,0,0, Coordinate.Type.RELATIVE);
    }

    /**
     * Creates a coordinate set from the specified coordinate objects.
     *
     * @param x The x coordinate object.
     * @param y The y coordinate object.
     * @param z The z coordinate object.
     *
     * @throws IllegalArgumentException If there is a mix of world coordinate and local coordinate types.
     * */
    public CoordinateSet(@NotNull Coordinate x, @NotNull Coordinate y, @NotNull Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;

        byte locals = 0;
        if(x.getType() == Coordinate.Type.LOCAL) locals++;
        if(y.getType() == Coordinate.Type.LOCAL) locals++;
        if(z.getType() == Coordinate.Type.LOCAL) locals++;

        if(locals != 0 && locals != 3) throw new IllegalArgumentException("Cannot combine local and world coordinates");
    }

    /**
     * Creates an absolute coordinate set from the specified coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     * */
    public CoordinateSet(double x, double y, double z) {
        this(new Coordinate(x), new Coordinate(y), new Coordinate(z));
    }

    /**
     * Creates a coordinate set from the specified coordinates and a type for those three coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     * @param type The type of all three coordinates.
     * */
    public CoordinateSet(double x, double y, double z, @NotNull Coordinate.Type type) {
        this(new Coordinate(type, x), new Coordinate(type, y), new Coordinate(type, z));
    }

    /**
     * Turns this coordinate set into a string that can be used in a command, using the specified display mode.
     *
     * @param mode The mode this coordinate set should be printed as.
     *
     * @return The string representing this coordinate set.
     * */
    public String getAs(@NotNull Coordinate.DisplayMode mode) {
        return x.getAs(mode) + " " + y.getAs(mode) + " " + z.getAs(mode);
    }

    /**
     * Turns this coordinate set into a string that can be used in a command, using the specified display mode.<br>
     *     Unlike the {@link CoordinateSet#getAs} method, this method only prints out the x and z coordinates for
     *     commands that use strictly two-dimensional coordinates, disregarding altitude.
     *
     * @param mode The mode this coordinate set should be printed as.
     *
     * @return The string representing this coordinate set's X and Z coordinates.
     * */
    public String getXZAs(@NotNull Coordinate.DisplayMode mode) {
        return x.getAs(mode) + " " + z.getAs(mode);
    }

    /**
     * Retrieves this coordinate set's x coordinate.
     *
     * @return The x coordinate for this coordinate set.
     * */
    @NotNull
    public Coordinate getX() {
        return x;
    }

    /**
     * Retrieves this coordinate set's y coordinate.
     *
     * @return The y coordinate for this coordinate set.
     * */
    @NotNull
    public Coordinate getY() {
        return y;
    }

    /**
     * Retrieves this coordinate set's z coordinate.
     *
     * @return The z coordinate for this coordinate set.
     * */
    @NotNull
    public Coordinate getZ() {
        return z;
    }

    @Override
    public String toString() {
        return getAs(Coordinate.DisplayMode.ENTITY_POS);
    }

    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateSet that = (CoordinateSet) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y) &&
                Objects.equals(z, that.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
