package com.energyxxer.commodore.commands.team;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class TeamLeaveCommand extends TeamCommand {
    private final Entity entity;

    public TeamLeaveCommand(Entity entity) {
        this.entity = entity;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "team leave \be0", entity);
    }
}
