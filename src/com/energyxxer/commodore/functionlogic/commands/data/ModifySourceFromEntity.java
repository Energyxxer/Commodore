package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;

public class ModifySourceFromEntity implements DataModifyCommand.ModifySource {
    private final Entity entity;
    private final NBTPath sourcePath;

    public ModifySourceFromEntity(Entity entity, NBTPath sourcePath) {
        this.entity = entity;
        this.sourcePath = sourcePath;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("from entity \be# " + sourcePath, entity);
    }
}
