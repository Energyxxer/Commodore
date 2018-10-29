package com.energyxxer.commodore.functionlogic.commands.drop;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.entity.Entity;

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
