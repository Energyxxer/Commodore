package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class ScoreRandom implements Command {
    @NotNull
    private final LocalScore score;
    private final int min;
    private final int max;

    public ScoreRandom(@NotNull LocalScore score, int min, int max) {
        this.score = score;
        this.min = min;
        this.max = max;

        score.assertNotNull();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players random " + score.getHolder() + " " + score.getObjective().getName() + " " + min + " " + max);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.scoreboard_random");
    }
}
