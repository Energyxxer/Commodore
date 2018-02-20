package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class ScoreList implements Command {
    private final Entity entity;

    public ScoreList() {
        this(null);
    }

    public ScoreList(Entity entity) {
        this.entity = entity;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return (entity != null) ? new CommandResolution(execContext, "scoreboard players list \be0", entity) : new CommandResolution(execContext, "scoreboard players list");
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
