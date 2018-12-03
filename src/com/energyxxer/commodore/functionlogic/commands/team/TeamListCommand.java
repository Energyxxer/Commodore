package com.energyxxer.commodore.functionlogic.commands.team;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.defaults.TeamReference;
import org.jetbrains.annotations.NotNull;

public class TeamListCommand extends TeamCommand {
    private final TeamReference reference;

    public TeamListCommand() {
        this(null);
    }

    public TeamListCommand(TeamReference reference) {
        this.reference = reference;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "team list" + (reference !=  null ? " " + reference : ""));
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
