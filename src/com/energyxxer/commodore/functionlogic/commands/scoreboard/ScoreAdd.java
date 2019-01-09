package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import org.jetbrains.annotations.NotNull;

public class ScoreAdd implements Command {
    @NotNull
    private final LocalScore score;
    private final int delta;

    public ScoreAdd(@NotNull LocalScore score, int delta) {
        this.score = score;
        this.delta = delta;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players " + ((delta >= 0) ? "add" : "remove") + " " + score.getHolder() + " " + score.getObjective().getName() + " " + Math.abs(delta));
    }

}
