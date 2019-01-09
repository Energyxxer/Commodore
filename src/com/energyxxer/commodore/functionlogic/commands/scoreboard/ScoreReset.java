package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.Objective;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ScoreReset implements Command {
    @Nullable
    private final Entity entity;
    @Nullable
    private final Objective objective;

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
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        StringBuilder sb = new StringBuilder("scoreboard players reset ");
        if(entity != null) {
            sb.append(entity);
        } else sb.append('*');
        if(objective != null) {
            sb.append(' ');
            sb.append(objective.getName());
        }
        return new CommandResolution(execContext, sb.toString());
    }

}
