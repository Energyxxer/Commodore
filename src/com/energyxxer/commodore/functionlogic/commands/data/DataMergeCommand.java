package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import org.jetbrains.annotations.NotNull;

public class DataMergeCommand extends DataCommand {

    @NotNull
    private final TagCompound nbt;

    public DataMergeCommand(@NotNull Entity entity, @NotNull TagCompound nbt) {
        super(entity);
        this.nbt = nbt;
    }

    public DataMergeCommand(@NotNull CoordinateSet pos, @NotNull TagCompound nbt) {
        super(pos);
        this.nbt = nbt;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        if(entity != null) return new CommandResolution(execContext, "data merge entity " + entity + " " + nbt.toHeadlessString());
        return new CommandResolution(execContext, "data merge block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + nbt.toHeadlessString());
    }
}
