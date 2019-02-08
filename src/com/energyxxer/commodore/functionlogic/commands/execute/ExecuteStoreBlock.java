package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.NumericNBTType;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class ExecuteStoreBlock extends ExecuteStore {
    @NotNull
    private final CoordinateSet position;
    @NotNull
    private final NBTPath path;
    @NotNull
    private final NumericNBTType type;
    private final double scale;

    public ExecuteStoreBlock(@NotNull CoordinateSet position, @NotNull NBTPath path, @NotNull NumericNBTType type) {
        this(StoreValue.DEFAULT, position, path, type);
    }

    public ExecuteStoreBlock(@NotNull StoreValue storeValue, @NotNull CoordinateSet position, @NotNull NBTPath path, @NotNull NumericNBTType type) {
        this(storeValue, position, path, type, 1);
    }

    public ExecuteStoreBlock(@NotNull CoordinateSet position, @NotNull NBTPath path, @NotNull NumericNBTType type, double scale) {
        this(StoreValue.DEFAULT, position, path, type, scale);
    }

    public ExecuteStoreBlock(@NotNull StoreValue storeValue, @NotNull CoordinateSet position, @NotNull NBTPath path, @NotNull NumericNBTType type, double scale) {
        super(storeValue);
        this.position = position;
        this.path = path;
        this.type = type;
        this.scale = scale;
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "block " + position.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + path + " " + type.toString().toLowerCase() + " " + CommandUtils.numberToPlainString(scale));
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return true;
    }

    @Override
    public boolean isAbsolute() {
        return true;
    }

    @NotNull
    public CoordinateSet getPosition() {
        return position;
    }

    @NotNull
    public NBTPath getPath() {
        return path;
    }

    @NotNull
    public NumericNBTType getType() {
        return type;
    }

    public double getScale() {
        return scale;
    }
}
