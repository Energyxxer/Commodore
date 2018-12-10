package com.energyxxer.commodore.functionlogic.commands.kill;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class KillCommand implements Command {

    @Nullable
    private final Entity entity;

    public KillCommand() {
        this(null);
    }

    public KillCommand(@Nullable Entity entity) {
        this.entity = entity;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return entity != null ? new CommandResolution(execContext, "kill \be0", entity) : new CommandResolution(execContext, "kill");
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return entity != null ? new ArrayList<>(entity.getScoreboardAccesses()) : Collections.emptyList();
    }
}
