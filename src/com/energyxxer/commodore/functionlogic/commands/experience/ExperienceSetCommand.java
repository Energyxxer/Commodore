package com.energyxxer.commodore.functionlogic.commands.experience;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class ExperienceSetCommand extends ExperienceCommand {

    private final int amount;
    @NotNull
    private final Unit unit;

    public ExperienceSetCommand(@NotNull Entity player, int amount, @NotNull Unit unit) {
        super(player);
        this.amount = amount;
        this.unit = unit;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "xp set " + player + " " + amount + " " + unit.toString().toLowerCase());
    }
}
