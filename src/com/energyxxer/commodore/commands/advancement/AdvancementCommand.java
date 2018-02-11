package com.energyxxer.commodore.commands.advancement;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class AdvancementCommand implements Command {

    public enum Action {
        GRANT, REVOKE
    }

    public enum Limit {
        EVERYTHING(false), FROM(true), ONLY(true), THROUGH(true), UNTIL(true);

        private final boolean takesAdvancement;

        Limit(boolean takesAdvancement) {
            this.takesAdvancement = takesAdvancement;
        }
    }

    private final Action action;
    private final Entity player;
    private final Limit limit;
    private String advancement = null;

    public AdvancementCommand(Action action, Entity player, Limit limit) {
        this(action, player, limit, null);
        if(limit.takesAdvancement)
            throw new IllegalArgumentException("Limit '" + limit + "' requires an advancement parameter");
    }

    public AdvancementCommand(Action action, Entity player, Limit limit, String advancement) {
        this.action = action;
        this.player = player;
        this.limit = limit;
        this.advancement = advancement;

        player.assertPlayer();

        if(advancement != null && !limit.takesAdvancement)
            System.out.println("[Commodore] [NOTICE] Limit '" + limit + "' doesn't require an advancement parameter, yet '" + advancement + "' was passed");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "advancement " + action.toString().toLowerCase() + " \be0 " + limit.toString().toLowerCase() + ((limit.takesAdvancement) ? " " + advancement : ""), player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
