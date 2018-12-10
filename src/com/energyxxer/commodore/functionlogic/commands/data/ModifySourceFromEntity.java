package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModifySourceFromEntity implements DataModifyCommand.ModifySource {
    @NotNull
    private final Entity entity;
    @Nullable
    private final NBTPath sourcePath;

    public ModifySourceFromEntity(@NotNull Entity entity) {
        this(entity, null);
    }

    public ModifySourceFromEntity(@NotNull Entity entity, @Nullable NBTPath sourcePath) {
        this.entity = entity;
        this.sourcePath = sourcePath;
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("from entity \be#" + ((sourcePath != null) ? " " + sourcePath : ""), entity);
    }
}
