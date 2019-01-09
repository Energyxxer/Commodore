package com.energyxxer.commodore.functionlogic.commands.advancement;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public class AdvancementCommand implements Command {

    public enum Action {
        GRANT, REVOKE
    }

    public enum Limit {
        EVERYTHING(false, false), FROM(true, false), ONLY(true, true), THROUGH(true, false), UNTIL(true, false);

        private final boolean takesAdvancement;
        private final boolean takesCriteria;

        Limit(boolean takesAdvancement, boolean takesCriteria) {
            this.takesAdvancement = takesAdvancement;
            this.takesCriteria = takesCriteria;
        }
    }

    @NotNull
    private final Action action;
    @NotNull
    private final Entity player;
    @NotNull
    private final Limit limit;
    @Nullable
    private final String advancement;
    @NotNull
    private final ArrayList<String> criteria = new ArrayList<>();

    public AdvancementCommand(Action action, Entity player, Limit limit) {
        this(action, player, limit, null);
        if(limit.takesAdvancement)
            throw new IllegalArgumentException("Limit '" + limit + "' requires an advancement parameter");
    }

    public AdvancementCommand(Action action, Entity player, Limit limit, String advancement) {
        this(action, player, limit, advancement, null);
    }

    public AdvancementCommand(@NotNull Action action, @NotNull Entity player, @NotNull Limit limit, String advancement, Collection<String> criteria) {
        this.action = action;
        this.player = player;
        this.limit = limit;
        this.advancement = limit.takesAdvancement ? advancement : null;
        if(limit.takesCriteria && criteria != null) this.criteria.addAll(criteria);

        player.assertPlayer();

        if(advancement != null && !limit.takesAdvancement)
            throw new IllegalArgumentException("Limit '" + limit + "' doesn't require an advancement parameter, yet '" + advancement + "' was passed");
        if(criteria != null && !limit.takesCriteria)
            throw new IllegalArgumentException("Limit '" + limit + "' doesn't require a criteria parameter, yet '" + criteria + "' was passed");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        StringBuilder criteriaStr = new StringBuilder();
        if(limit.takesCriteria && !criteria.isEmpty()) {
            for(String str : criteria) {
                criteriaStr.append(" ");
                criteriaStr.append(str);
            }
        }
        return new CommandResolution(execContext,
                "advancement " + action.toString().toLowerCase() + " " + player + " " + limit.toString().toLowerCase() + ((limit.takesAdvancement) ? " " + advancement : "") + ((limit.takesCriteria) ? criteriaStr.toString() : ""));
    }

}
