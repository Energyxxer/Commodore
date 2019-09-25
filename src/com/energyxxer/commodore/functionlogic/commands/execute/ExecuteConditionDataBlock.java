package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.commands.data.DataHolderBlock;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class ExecuteConditionDataBlock extends ExecuteConditionDataHolder {

    public ExecuteConditionDataBlock(@NotNull ConditionType flowController, @NotNull CoordinateSet pos, @NotNull NBTPath path) {
        super(flowController, new DataHolderBlock(pos), path);
    }

    @NotNull
    public CoordinateSet getPos() {
        return ((DataHolderBlock) getHolder()).getPos();
    }
}
