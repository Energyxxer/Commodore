package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.commands.data.DataHolderEntity;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class ExecuteConditionDataEntity extends ExecuteConditionDataHolder {

    public ExecuteConditionDataEntity(@NotNull ConditionType flowController, @NotNull Entity entity, @NotNull NBTPath path) {
        super(flowController, new DataHolderEntity(entity), path);
    }

    @NotNull
    public Entity getEntity() {
        return ((DataHolderEntity) getHolder()).getEntity();
    }
}
