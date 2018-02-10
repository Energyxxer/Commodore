package com.energyxxer.commodore.commands.experience;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class ExperienceQueryCommand extends ExperienceCommand {

    private Unit unit;

    public ExperienceQueryCommand(Entity player, Unit unit) {
        super(player);
        this.unit = unit;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "xp query \be0 " + unit.toString().toLowerCase(), player);
    }
}
