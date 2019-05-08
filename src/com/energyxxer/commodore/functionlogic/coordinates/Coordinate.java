package com.energyxxer.commodore.functionlogic.coordinates;

import com.energyxxer.commodore.CommandUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

/**
 * Describes a position in one of the world axis. May be one of three types, seen in {@link Type}.
 * */
public class Coordinate {
    /**
     * Describes the different coordinate types.
     * */
    public enum Type {
        /**
         * Describes an offset from the world's origin.
         * */
        ABSOLUTE(""),
        /**
         * Describes an offset from the current execution context.
         * */
        RELATIVE("~"),
        /**
         * Describes an offset from the current execution context's anchor, rotated the same as the execution context.
         * */
        LOCAL("^");

        /**
         * The symbol(s) that precede the coordinate number to specify that the coordinate is of this type.
         * */
        public final String prefix;

        /**
         * Creates a coordinate type with the given prefix.
         *
         * @param prefix The symbol(s) that precede the coordinate number to specify that the coordinate is of this type.
         * */
        Type(String prefix) {
            this.prefix = prefix;
        }
    }

    /**
     * Describes the different ways a coordinate should be expressed.
     * */
    public enum DisplayMode {
        /**
         * Specifies that the coordinate should be displayed as if to refer to a block.
         * */
        BLOCK_POS(true),
        /**
         * Specifies that the coordinate should be displayed as if to refer to an entity position.
         * */
        ENTITY_POS(false);

        /**
         * Whether absolute values should floor decimals for this display mode.
         * */
        final boolean floor;

        /**
         * Creates a display mode with the given floor property.
         *
         * @param floor Whether absolute values should be truncated.
         * */
        DisplayMode(boolean floor) {
            this.floor = floor;
        }

        /**
         * Retrieves this display mode's floor flag.
         *
         * @return <code>true</code> if absolute values should be floored for this display mode, <code>false</code>
         * otherwise.
         * */
        public boolean doFloor() {
            return floor;
        }
    }

    /**
     * The type of this coordinate.
     * */
    @NotNull
    private final Type type;
    /**
     * The distance from the origin this coordinate represents for its axis.
     * */
    private final double coord;

    /**
     * Creates a coordinate object for the given distance in an absolute point of reference.
     *
     * @param coord The distance from the origin this coordinate represents for its axis.
     * */
    public Coordinate(double coord) {
        this(Type.ABSOLUTE, coord);
    }

    /**
     * Creates a coordinate of the given type with the specified magnitude.
     *
     * @param type The type of coordinate this new coordinate should be.
     * @param coord The distance from the point of reference this coordinate represents for its axis.
     * */
    public Coordinate(@NotNull Type type, double coord) {
        this.type = type;
        this.coord = coord;
        assertFinite(coord, "magnitude");
    }

    /**
     * Retrieves this coordinate's type.
     *
     * @return The type of this coordinate.
     * */
    @NotNull
    public Type getType() {
        return type;
    }

    /**
     * Retrieves this coordinate's distance from the point of reference; that is, its magnitude in its axis.
     *
     * @return The distance from the point of reference of this coordinate.
     * */
    public double getCoord() {
        return coord;
    }

    /**
     * Checks if this coordinate is idempotent; that is, if applying this coordinate twice in a row in the same axis
     * would give the same effect as applying it once.
     *
     * @return <code>true</code> if this coordinate is idempotent, <code>false</code> otherwise.
     * */
    public boolean isIdempotent() {
        return type == Type.ABSOLUTE;
    }

    /**
     * Checks if this coordinate is significant; that is, if applying this coordinate once would differ from not
     * applying it at all.
     *
     * @return <code>true</code> if this coordinate is significant, <code>false</code> otherwise.
     * */
    public boolean isSignificant() {
        return type != Type.RELATIVE || coord != 0;
    }

    /**
     * Turns this coordinate into a string that can be used in a command, using the specified display mode.
     *
     * @param mode The mode this coordinate should be printed as.
     *
     * @return The string representing this coordinate.
     * */
    public String getAs(DisplayMode mode) {
        double num = coord;

        if(mode.doFloor() && type == Type.ABSOLUTE) num = Math.floor(num);

        String numStr;

        if((mode.doFloor() && type == Type.ABSOLUTE) || (num % 1 == 0 && type != Type.ABSOLUTE))
            numStr = CommandUtils.numberToPlainString(num);
        else numStr = CommandUtils.numberToStringNoScientific(num);

        if(num == 0 && type != Type.ABSOLUTE) numStr = "";

        return type.prefix + numStr;
    }

    @Override
    public String toString() {
        return getAs(DisplayMode.ENTITY_POS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that.coord, coord) == 0 &&
                type == that.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, coord);
    }
}
