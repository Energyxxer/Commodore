package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.effect.StatusEffect;
import com.energyxxer.commodore.entity.Entity;

public class EffectGiveCommand extends EffectCommand {
    private StatusEffect effect;

    public EffectGiveCommand(Entity entity, StatusEffect effect) {
        super(entity);
        this.effect = effect;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "effect give " + entity.getSelectorAs(sender) + " " + effect;
    }
}
