package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.nbt.DataHolder;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderBlock;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderEntity;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public abstract class DataCommand implements Command {

    protected final DataHolder holder;

    public DataCommand(@NotNull Entity entity) {
        this(new DataHolderEntity(entity));
    }

    public DataCommand(@NotNull CoordinateSet pos) {
        this(new DataHolderBlock(pos));
    }

    public DataCommand(@NotNull DataHolder holder) {
        this.holder = holder;
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("nbt.access");
        holder.assertAvailable();
    }
}
