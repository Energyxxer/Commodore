package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ScoreTest implements Command {
    @NotNull
    private final LocalScore score;
    @Nullable
    private final Integer min;
    @Nullable
    private final Integer max;

    public ScoreTest(@NotNull LocalScore score, @Nullable Integer min, @Nullable Integer max) {
        this.score = score;
        this.min = min;
        this.max = max;

        score.assertNotNull();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players test " + score.getHolder() + " " + score.getObjective().getName() + " " + (min != null ? min : "*") + " " + (max != null ? max : "*"));
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.scoreboard_test");
    }
}
