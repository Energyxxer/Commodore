package com.energyxxer.commodore.commands.effect;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
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
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "effect clear \be0" + ((type != null) ? " " + type : ""), entity);
    }
}
