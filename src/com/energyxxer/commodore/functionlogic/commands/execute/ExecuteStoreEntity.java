package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderEntity;
import com.energyxxer.commodore.functionlogic.nbt.NumericNBTType;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class ExecuteStoreEntity extends ExecuteStoreDataHolder {

    public ExecuteStoreEntity(@NotNull Entity entity, @NotNull NBTPath path, @NotNull NumericNBTType type) {
        this(StoreValue.DEFAULT, entity, path, type);
    }

    public ExecuteStoreEntity(@NotNull StoreValue storeValue, @NotNull Entity entity, @NotNull NBTPath path, @NotNull NumericNBTType type) {
        this(storeValue, entity, path, type, 1);
    }

    public ExecuteStoreEntity(@NotNull Entity entity, @NotNull NBTPath path, @NotNull NumericNBTType type, double scale) {
        this(StoreValue.DEFAULT, entity, path, type, scale);
    }

    public ExecuteStoreEntity(@NotNull StoreValue storeValue, @NotNull Entity entity, @NotNull NBTPath path, @NotNull NumericNBTType type, double scale) {
        super(storeValue, new DataHolderEntity(entity), path, type, scale);
    }

    @NotNull
    public Entity getEntity() {
        return ((DataHolderEntity) getHolder()).getEntity();
    }
}
