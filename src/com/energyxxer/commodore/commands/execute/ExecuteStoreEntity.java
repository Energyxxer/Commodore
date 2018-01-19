package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.nbt.NBTPath;
import com.energyxxer.commodore.nbt.NumericNBTType;

public class ExecuteStoreEntity extends ExecuteStore {
    private Entity entity;
    private NBTPath path;
    private NumericNBTType type;
    private double scale;

    public ExecuteStoreEntity(Entity entity, NBTPath path, NumericNBTType type) {
        this(StoreValue.getDefault(), entity, path, type);
    }

    public ExecuteStoreEntity(StoreValue storeValue, Entity entity, NBTPath path, NumericNBTType type) {
        this(storeValue, entity, path, type, 1);
    }

    public ExecuteStoreEntity(Entity entity, NBTPath path, NumericNBTType type, double scale) {
        this(StoreValue.getDefault(), entity, path, type, scale);
    }

    public ExecuteStoreEntity(StoreValue storeValue, Entity entity, NBTPath path, NumericNBTType type, double scale) {
        super(storeValue);
        this.entity = entity;
        this.path = path;
        this.type = type;
        this.scale = scale;
        if(entity.getLimit() < 0 || entity.getLimit() > 1) throw new IllegalArgumentException("Only one entity is allowed, but passed entity (" + entity + ") allows for more than one.");
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult(this.getStarter() + "entity " + entity.getSelectorAs(sender) + " " + path + " " + type.toString().toLowerCase() + " " + CommandUtils.toString(scale));
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
