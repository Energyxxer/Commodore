package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.nbt.NBTPath;
import com.energyxxer.commodore.nbt.NumericNBTType;

public class ExecuteStoreBlock extends ExecuteStore {
    private CoordinateSet position;
    private NBTPath path;
    private NumericNBTType type;
    private double scale;

    public ExecuteStoreBlock(CoordinateSet position, NBTPath path, NumericNBTType type) {
        this(StoreValue.getDefault(), position, path, type);
    }

    public ExecuteStoreBlock(StoreValue storeValue, CoordinateSet position, NBTPath path, NumericNBTType type) {
        this(storeValue, position, path, type, 1);
    }

    public ExecuteStoreBlock(CoordinateSet position, NBTPath path, NumericNBTType type, double scale) {
        this(StoreValue.getDefault(), position, path, type, scale);
    }

    public ExecuteStoreBlock(StoreValue storeValue, CoordinateSet position, NBTPath path, NumericNBTType type, double scale) {
        super(storeValue);
        this.position = position;
        this.path = path;
        this.type = type;
        this.scale = scale;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "block " + position.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + path + " " + type.toString().toLowerCase() + " " + CommandUtils.toString(scale));
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
        return false;
    }
}
