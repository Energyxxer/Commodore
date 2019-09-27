package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderBlock;
import com.energyxxer.commodore.functionlogic.nbt.NumericNBTType;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class ExecuteStoreBlock extends ExecuteStoreDataHolder {

    public ExecuteStoreBlock(@NotNull CoordinateSet pos, @NotNull NBTPath path, @NotNull NumericNBTType type) {
        this(StoreValue.DEFAULT, pos, path, type);
    }

    public ExecuteStoreBlock(@NotNull StoreValue storeValue, @NotNull CoordinateSet pos, @NotNull NBTPath path, @NotNull NumericNBTType type) {
        this(storeValue, pos, path, type, 1);
    }

    public ExecuteStoreBlock(@NotNull CoordinateSet pos, @NotNull NBTPath path, @NotNull NumericNBTType type, double scale) {
        this(StoreValue.DEFAULT, pos, path, type, scale);
    }

    public ExecuteStoreBlock(@NotNull StoreValue storeValue, @NotNull CoordinateSet pos, @NotNull NBTPath path, @NotNull NumericNBTType type, double scale) {
        super(storeValue, new DataHolderBlock(pos), path, type, scale);
    }

    @NotNull
    public CoordinateSet getPos() {
        return ((DataHolderBlock) getHolder()).getPos();
    }
}
