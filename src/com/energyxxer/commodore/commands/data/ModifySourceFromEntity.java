package com.energyxxer.commodore.commands.data;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.nbt.path.NBTPath;

public class ModifySourceFromEntity implements ModifySource {
    private final Entity entity;
    private final NBTPath sourcePath;

    public ModifySourceFromEntity(Entity entity, NBTPath sourcePath) {
        this.entity = entity;
        this.sourcePath = sourcePath;
    }

    @Override
    public ModifySourceResolution resolve() {
        return new ModifySourceResolution("from entity \be# " + sourcePath, entity);
    }
}
