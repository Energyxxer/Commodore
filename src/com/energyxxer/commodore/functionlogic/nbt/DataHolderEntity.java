package com.energyxxer.commodore.functionlogic.nbt;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class DataHolderEntity implements DataHolder {

    @NotNull
    private Entity entity;

    public DataHolderEntity(@NotNull Entity entity) {
        this.entity = entity;
        entity.assertEntityFriendly();
    }

    @NotNull
    @Override
    public String resolve() {
        return "entity " + entity;
    }

    @Override
    public void assertSingle() {
        entity.assertSingle();
    }

    public void assertAvailable() {
        entity.assertAvailable();
    }

    @NotNull
    public Entity getEntity() {
        return entity;
    }

    @Override
    public @NotNull String getTextComponentKey() {
        return "entity";
    }

    @Override
    public @NotNull String getTextComponentValue() {
        return entity.toString();
    }
}
