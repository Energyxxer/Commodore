package com.energyxxer.commodore.functionlogic.commands.recipe;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class RecipeCommand implements Command {
    public enum Action {
        GIVE, TAKE
    }

    private final Action action;
    private final Entity player;
    private final String recipeName;

    public RecipeCommand(Action action, Entity player) {
        this(action, player, "*");
    }

    public RecipeCommand(Action action, Entity player, String recipeName) {
        this.action = action;
        this.player = player;
        this.recipeName = recipeName;

        player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "recipe " + action.toString().toLowerCase() + " \be0 " + recipeName, player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
