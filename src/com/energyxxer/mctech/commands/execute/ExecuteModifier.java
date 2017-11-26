package com.energyxxer.mctech.commands.execute;

import com.energyxxer.mctech.entity.Entity;

public interface ExecuteModifier {
    SubCommandResult getSubCommand(Entity sender);

    boolean isIdempotent();
    boolean isSignificant();
    boolean isAbsolute();
}
