package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.Objective;
import org.jetbrains.annotations.NotNull;

public class ObjectivesAddCommand implements Command {
    @NotNull
    private final Objective objective;

    public ObjectivesAddCommand(@NotNull Objective objective) {
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
