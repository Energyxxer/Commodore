package com.energyxxer.commodore.functionlogic.commands.team;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.defaults.TeamReference;
import org.jetbrains.annotations.NotNull;

public class TeamJoinCommand extends TeamCommand {
    private final TeamReference reference;
    private final Entity entity;

    public TeamJoinCommand(TeamReference reference) {
        this(reference, null);
    }

    public TeamJoinCommand(TeamReference reference, Entity entity) {
        this.reference = reference;
        this.entity = entity;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return (entity != null) ? new CommandResolution(execContext, "team join " + reference + " \be0", entity) : new CommandResolution(execContext, "team join " + reference);
    }
}
