package com.energyxxer.commodore.functionlogic.commands.team;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.defaults.TeamReference;
import org.jetbrains.annotations.NotNull;

public class TeamEmptyCommand extends TeamCommand {
    @NotNull
    private final TeamReference reference;

    public TeamEmptyCommand(@NotNull TeamReference reference) {
        this.reference = reference;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "team empty " + reference.toSafeString());
    }
}
