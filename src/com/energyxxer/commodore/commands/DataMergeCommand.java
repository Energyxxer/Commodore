package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.nbt.TagCompound;

public class DataMergeCommand extends DataCommand {

    private TagCompound nbt;

    public DataMergeCommand(Entity entity, TagCompound nbt) {
        super(entity);
        this.nbt = nbt;
    }

    public DataMergeCommand(CoordinateSet pos, TagCompound nbt) {
        super(pos);
        this.nbt = nbt;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "data merge " + (entity != null ? "entity " + CommandUtils.getRawReference(entity, sender) : "block " + pos.toString()) + " " + nbt.toHeadlessString();
    }
}
