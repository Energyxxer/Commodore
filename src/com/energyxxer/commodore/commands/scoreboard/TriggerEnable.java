package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.Objective;
import org.jetbrains.annotations.NotNull;

public class TriggerEnable implements Command {
    private final Entity player;
    private final Objective objective;

    public TriggerEnable(Entity player, Objective objective) {
        this.player = player;
        this.objective = objective;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players enable \be0 " + objective.getName(), player);
    }
}
