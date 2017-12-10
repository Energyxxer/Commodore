package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.effect.StatusEffect;
import com.energyxxer.commodore.entity.Entity;

public class EffectGiveCommand extends EffectCommand {
    private Entity entity;
    private StatusEffect effect;

    public EffectGiveCommand(Entity entity, StatusEffect effect) {
        this.entity = entity;
        this.effect = effect;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "effect give " + entity.getSelectorAs(sender) + " " + effect;
    }
}
