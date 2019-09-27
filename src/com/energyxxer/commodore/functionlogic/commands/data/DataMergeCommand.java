package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.DataHolder;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderBlock;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderEntity;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import org.jetbrains.annotations.NotNull;

public class DataMergeCommand extends DataCommand {

    @NotNull
    private final TagCompound nbt;

    public DataMergeCommand(@NotNull Entity entity, @NotNull TagCompound nbt) {
        this(new DataHolderEntity(entity), nbt);
    }

    public DataMergeCommand(@NotNull CoordinateSet pos, @NotNull TagCompound nbt) {
        this(new DataHolderBlock(pos), nbt);
    }

    public DataMergeCommand(@NotNull DataHolder holder, @NotNull TagCompound nbt) {
        super(holder);
        this.nbt = nbt;

        holder.assertSingle();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "data merge " + holder.resolve() + " " + nbt.toHeadlessString());
    }
}
