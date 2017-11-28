package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.EffectType;

public class EffectCommand implements Command {
    public enum Action {
        GIVE, CLEAR
    }

    private Action action;
    private Entity entity;
    private EffectType effect;
    private int amplifier = -1;
    private int duration;

    @Override
    public String getRawCommand(Entity sender) {
        return "effect " + action.toString().toLowerCase() + " " + entity;
    }
}
