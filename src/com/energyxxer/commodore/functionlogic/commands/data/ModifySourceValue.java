package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.nbt.NBTTag;

public class ModifySourceValue implements ModifySource {
    private final NBTTag value;

    public ModifySourceValue(NBTTag value) {
        this.value = value;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("value " + value.toHeadlessString());
    }
}
