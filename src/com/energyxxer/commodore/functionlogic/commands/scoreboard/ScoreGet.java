package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class ScoreGet implements Command {
    @NotNull
    private final LocalScore score;

    public ScoreGet(@NotNull LocalScore score) {
        this.score = score;
        score.getHolder().assertSingle();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players get " + score.holderToString() + " " + score.objectiveToString());
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.scoreboard_get");
        score.assertAvailable();
    }

}
