package com.energyxxer.commodore.functionlogic.commands.tag;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TagCommand implements Command {
    public enum Action {
        ADD, REMOVE
    }

    @NotNull
    private final Action action;
    @NotNull
    private final Entity entity;
    @NotNull
    private final String tag;

    public TagCommand(@NotNull Action action, @NotNull Entity entity, @NotNull String tag) {
        this.action = action;
        this.entity = entity;
        this.tag = tag;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "tag \be0 " + action.toString().toLowerCase() + " " + tag, entity);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(entity.getScoreboardAccesses());
    }
}
