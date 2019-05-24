package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public abstract class DataCommand implements Command {

    protected final Entity entity;
    protected final CoordinateSet pos;

    public DataCommand(@NotNull Entity entity) {
        this.pos = null;
        this.entity = entity;
    }

    public DataCommand(@NotNull CoordinateSet pos) {
        this.entity = null;
        this.pos = pos;
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("nbt.access");
    }
}
