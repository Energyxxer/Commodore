package com.energyxxer.commodore.coordinates;

public class Coordinate {
    public enum Type {
        ABSOLUTE(""), RELATIVE("~"), LOCAL("^");

        public final String prefix;

        Type(String prefix) {
            this.prefix = prefix;
        }
    }

    public enum DisplayMode {
        BLOCK_POS(true), ENTITY_POS(false);

        boolean truncate;

        DisplayMode(boolean truncate) {
            this.truncate = truncate;
        }

        public boolean doTruncate() {
            return truncate;
        }
    }

    private Type type;
    private double coord;

    public Coordinate(double coord) {
        this(Type.ABSOLUTE, coord);
    }

    public Coordinate(Type type, double coord) {
        this.type = type;
        this.coord = coord;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getCoord() {
        return coord;
    }

    public void setCoord(double coord) {
        this.coord = coord;
    }

    public boolean isIdempotent() {
        return type == Type.ABSOLUTE;
    }

    public boolean isSignificant() {
        return type == Type.ABSOLUTE || coord == 0;
    }

    public String getAs(DisplayMode mode) {
        double num = coord;

        if(mode.doTruncate() && type == Type.ABSOLUTE) num = Math.floor(num);

        String numStr;

        if((mode.doTruncate() && type == Type.ABSOLUTE) || (num % 1 == 0 && type != Type.ABSOLUTE)) numStr = String.valueOf((int) num);
        else numStr = String.valueOf(num);

        if(num == 0 && type != Type.ABSOLUTE) numStr = "";

        return type.prefix + numStr;
    }

    @Override
    public String toString() {
        return getAs(DisplayMode.ENTITY_POS);
    }
}
