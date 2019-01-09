package com.energyxxer.commodore.functionlogic.commands.experience;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class ExperienceQueryCommand extends ExperienceCommand {

    @NotNull
    private final Unit unit;

    public ExperienceQueryCommand(@NotNull Entity player, @NotNull Unit unit) {
        super(player);
        this.unit = unit;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "xp query " + player + " " + unit.toString().toLowerCase());
    }

}
