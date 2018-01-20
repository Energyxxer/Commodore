package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class RecipeCommand implements Command {
    public enum Action {
        GIVE, TAKE
    }

    private Action action;
    private Entity player;
    private String recipeName;

    public RecipeCommand(Action action, Entity player) {
        this(action, player, "*");
    }

    public RecipeCommand(Action action, Entity player, String recipeName) {
        this.action = action;
        this.player = player;
        this.recipeName = recipeName;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "recipe " + action.toString().toLowerCase() + " " + player.getSelectorAs(sender) + " " + recipeName;
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
