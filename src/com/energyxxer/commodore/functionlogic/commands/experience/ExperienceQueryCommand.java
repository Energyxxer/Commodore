package com.energyxxer.commodore.functionlogic.commands.experience;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class ExperienceQueryCommand extends ExperienceCommand {

    private final Unit unit;

    public ExperienceQueryCommand(Entity player, Unit unit) {
        super(player);
        this.unit = unit;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "xp query \be0 " + unit.toString().toLowerCase(), player);
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
