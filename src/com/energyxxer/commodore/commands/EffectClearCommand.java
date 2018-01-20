package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.EffectType;

public class EffectClearCommand extends EffectCommand {
    private EffectType type;

    public EffectClearCommand(Entity entity) {
        this(entity, null);
    }

    public EffectClearCommand(Entity entity, EffectType type) {
        super(entity);
        this.type = type;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "effect clear " + entity.getSelectorAs(sender) + ((type != null) ? " " + type : "");
    }
}
