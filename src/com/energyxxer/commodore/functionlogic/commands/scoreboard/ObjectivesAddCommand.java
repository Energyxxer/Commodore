package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.CommodoreException;
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
        return new CommandResolution(execContext, "scoreboard objectives add " + objective.toString() + " " + objective.getType() + ((objective.getDisplayName() != null) ? " " + objective.getDisplayName() : ""));
    }
}
