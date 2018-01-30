package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TagCommand implements Command {
    public enum Action {
        ADD, REMOVE
    }

    private Action action;
    private Entity entity;
    private String tag;

    public TagCommand(Action action, Entity entity, String tag) {
        this.action = action;
        this.entity = entity;
        this.tag = tag;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "tag " + action.toString().toLowerCase() + " " + entity.getSelectorAs(sender) + " " + tag;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "tag" + action.toString().toLowerCase() + " \be0 " + tag, entity);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(entity.getScoreboardAccesses());
    }
}
