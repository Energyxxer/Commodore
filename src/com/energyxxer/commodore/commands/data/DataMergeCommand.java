package com.energyxxer.commodore.commands.data;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
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
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        if(entity != null) return new CommandResolution(execContext, "data merge entity \be0 " + nbt.toHeadlessString(), entity);
        return new CommandResolution(execContext, "data merge block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + nbt.toHeadlessString());
    }
}
