package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreReset implements Command {
    @Nullable
    private final Entity entity;
    @Nullable
    private final Objective objective;

    private final ArrayList<ScoreboardAccess> accesses;

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
        this.entity = entity;
        this.objective = objective;

        this.accesses = new ArrayList<>();

        accesses.add(new ScoreboardAccess(new LocalScore(objective, entity).getMacroScores(), ScoreboardAccess.AccessType.WRITE));
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accesses;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();
        StringBuilder sb = new StringBuilder("scoreboard players reset ");
        if(entity != null) {
            sb.append("\be0");
            embeddables.add(entity);
        } else sb.append('*');
        if(objective != null) {
            sb.append(' ');
            sb.append(objective.getName());
        }
        return new CommandResolution(execContext, sb.toString(), embeddables);
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
