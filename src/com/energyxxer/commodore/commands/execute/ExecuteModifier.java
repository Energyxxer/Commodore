package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;

public interface ExecuteModifier {
    SubCommandResult getSubCommand(Entity sender);

    boolean isIdempotent();

    boolean isSignificant();

    boolean isAbsolute();
}
