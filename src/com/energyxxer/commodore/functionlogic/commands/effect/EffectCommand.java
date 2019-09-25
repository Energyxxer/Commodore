package com.energyxxer.commodore.functionlogic.commands.effect;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

public abstract class EffectCommand implements Command {
    @NotNull
    protected final Entity entity;

    public EffectCommand(@NotNull Entity entity) {
        this.entity = entity;
    }

    @Override
    public void assertAvailable() {
        entity.assertAvailable();
    }
}
