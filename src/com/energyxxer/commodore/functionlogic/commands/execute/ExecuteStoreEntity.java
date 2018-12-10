package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.NumericNBTType;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class ExecuteStoreEntity extends ExecuteStore {
    @NotNull
    private final Entity entity;
    @NotNull
    private final NBTPath path;
    @NotNull
    private final NumericNBTType type;
    private final double scale;

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
        super(storeValue);
        this.entity = entity;
        this.path = path;
        this.type = type;
        this.scale = scale;
        if(entity.getLimit() < 0 || entity.getLimit() > 1) throw new IllegalArgumentException("Only one entity is allowed, but passed entity (" + entity + ") allows for more than one.");
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "entity \be0 " + path + " " + type.toString().toLowerCase() + " " + CommandUtils.numberToPlainString(scale), entity);
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        return entity.getScoreboardAccesses();
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
}
