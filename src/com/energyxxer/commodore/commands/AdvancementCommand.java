package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;

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

    private Action action;
    private Entity player;
    private Limit limit;
    private String advancement = null;

    public AdvancementCommand(Action action, Entity player, Limit limit) {
        this(action, player, limit, null);
        if(limit.takesAdvancement) throw new IllegalArgumentException("Limit '" + limit + "' requires an advancement parameter");
    }

    public AdvancementCommand(Action action, Entity player, Limit limit, String advancement) {
        this.action = action;
        this.player = player;
        this.limit = limit;
        this.advancement = advancement;
        if(advancement != null && !limit.takesAdvancement) System.out.println("[Commodore] [NOTICE] Limit '" + limit + "' doesn't require an advancement parameter, yet '" + advancement + "' was passed");
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "advancement " + action.toString().toLowerCase() + " " + player.getSelectorAs(sender) + " " + limit.toString().toLowerCase() + ((limit.takesAdvancement) ? " " + advancement : "");
    }
}
