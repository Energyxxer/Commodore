package com.energyxxer.commodore.commands.drop;

import com.energyxxer.commodore.commands.CommandDelegateResolution;
import com.energyxxer.commodore.entity.Entity;

public class DropFromKill implements DropSource {
    private final Entity entity;

    public DropFromKill(Entity entity) {
        this.entity = entity;
        entity.assertSingle();
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("kill \be#", entity);
    }
}
