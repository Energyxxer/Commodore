package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import org.jetbrains.annotations.NotNull;

public class ScoreSet implements Command {
    @NotNull
    private final LocalScore score;
    private final int value;

    public ScoreSet(@NotNull LocalScore score, int value) {
        this.score = score;
        this.value = value;

        score.assertNotNull();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players set " + score.getHolder() + " " + score.getObjective().getName() + " " + value);
    }

}
