package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.effect.StatusEffect;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class EffectGiveCommand extends EffectCommand {
    private StatusEffect effect;

    public EffectGiveCommand(Entity entity, StatusEffect effect) {
        super(entity);
        this.effect = effect;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "effect give \be0 " + effect, entity);
    }
}
