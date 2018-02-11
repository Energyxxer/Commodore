package com.energyxxer.commodore.commands.team;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.TeamReference;
import org.jetbrains.annotations.NotNull;

public class TeamRemoveCommand extends TeamCommand {
    private final TeamReference reference;

    public TeamRemoveCommand(TeamReference reference) {
        this.reference = reference;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "team remove " + reference);
    }
}
