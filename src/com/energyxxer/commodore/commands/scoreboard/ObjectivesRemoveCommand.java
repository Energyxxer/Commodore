package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.Objective;
import org.jetbrains.annotations.NotNull;

public class ObjectivesRemoveCommand implements Command {
    private final Objective objective;

    public ObjectivesRemoveCommand(Objective objective) {
        this.objective = objective;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard objectives remove " + objective.getName());
    }
}
