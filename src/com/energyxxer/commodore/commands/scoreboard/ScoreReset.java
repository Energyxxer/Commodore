package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.Objective;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreReset implements Command {
    private final Entity entity;
    private final Objective objective;

    private final ArrayList<ScoreboardAccess> accesses;

    public ScoreReset() {
        this(null, null);
    }

    public ScoreReset(Entity entity) {
        this(entity, null);
    }

    public ScoreReset(Objective objective) {
        this(null, objective);
    }

    public ScoreReset(Entity entity, Objective objective) {
        this.entity = entity;
        this.objective = objective;

        this.accesses = new ArrayList<>();
        //TODO: Allow for scoreboard accesses to exclude holder and objective
        if(entity != null && objective != null) {
            accesses.add(new ScoreboardAccess(new LocalScore(objective, entity).getMacroScores(), ScoreboardAccess.AccessType.WRITE));
        }
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
}
