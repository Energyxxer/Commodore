package com.energyxxer.commodore.commands.data;

import com.energyxxer.commodore.nbt.NBTTag;

public class ModifySourceValue implements ModifySource {
    private final NBTTag value;

    public ModifySourceValue(NBTTag value) {
        this.value = value;
    }

    @Override
    public ModifySourceResolution resolve() {
        return new ModifySourceResolution("value " + value.toHeadlessString());
    }
}
