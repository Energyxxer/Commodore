package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.nbt.NBTTag;
import org.jetbrains.annotations.NotNull;

public class ModifySourceValue implements DataModifyCommand.ModifySource {
    @NotNull
    private final NBTTag value;

    public ModifySourceValue(@NotNull NBTTag value) {
        this.value = value;
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("value " + value.toHeadlessString());
    }
}
