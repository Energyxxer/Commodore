package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.entity.Entity;

public class DataHolderEntity implements DataHolder {

    private Entity entity;

    public DataHolderEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String resolve() {
        return "entity " + entity;
    }

    @Override
    public void assertSingle() {
        entity.assertSingle();
    }

    public void assertAvailable() {
    }
}
