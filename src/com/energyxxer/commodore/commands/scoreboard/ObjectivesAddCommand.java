package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.Objective;
import org.jetbrains.annotations.NotNull;

public class ObjectivesAddCommand implements Command {
    private final Objective objective;

    public ObjectivesAddCommand(Objective objective) {
        this.objective = objective;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        if(objective.getName().length() > Objective.MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Objective name '" + objective.getName() + "' exceeds the max length of " + Objective.MAX_NAME_LENGTH + " characters when module prefix is included");
        }
        return new CommandResolution(execContext, "scoreboard objectives add " + objective.getName() + " " + objective.getType() + ((objective.getDisplayName() != null) ? " " + objective.getDisplayName() : ""));
    }
}
