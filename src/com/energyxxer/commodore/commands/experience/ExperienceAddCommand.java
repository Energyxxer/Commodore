package com.energyxxer.commodore.commands.experience;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class ExperienceAddCommand extends ExperienceCommand {

    private int amount;
    private Unit unit;

    public ExperienceAddCommand(Entity player, int amount, Unit unit) {
        super(player);
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "xp add \be0 " + amount + " " + unit.toString().toLowerCase(), player);
    }
}
