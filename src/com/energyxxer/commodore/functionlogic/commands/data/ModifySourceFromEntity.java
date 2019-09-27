package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderEntity;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class ModifySourceFromEntity extends ModifySourceFromHolder {

    public ModifySourceFromEntity(@NotNull Entity entity) {
        this(entity, null);
    }

    public ModifySourceFromEntity(@NotNull Entity entity, @Nullable NBTPath sourcePath) {
        super(new DataHolderEntity(entity), sourcePath);
    }
}
