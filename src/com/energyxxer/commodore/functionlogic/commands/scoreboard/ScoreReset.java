package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.Objective;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ScoreReset implements Command {
    @NotNull
    private final LocalScore score;

    public ScoreReset() {
        this(null, null);
    }

    public ScoreReset(@Nullable Entity entity) {
        this(entity, null);
    }

    public ScoreReset(@Nullable Objective objective) {
        this(null, objective);
    }

    public ScoreReset(@Nullable Entity entity, @Nullable Objective objective) {
        this(new LocalScore(entity, objective));
    }

    public ScoreReset(@NotNull LocalScore score) {
        this.score = score;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        StringBuilder sb = new StringBuilder("scoreboard players reset " + score.holderToString() + " ");
        if(score.getObjective() != null) {
            sb.append(' ');
            sb.append(score.objectiveToString());
        }
        return new CommandResolution(execContext, sb.toString());
    }

}
