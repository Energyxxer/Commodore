package com.energyxxer.commodore.functionlogic.commands.trigger;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.Objective;
import org.jetbrains.annotations.NotNull;

public class TriggerCommand implements Command {

    public enum Action {
        SET(false), ADD(true);

        final boolean readsScore;

        Action(boolean readsScore) {
            this.readsScore = readsScore;
        }
    }

    @NotNull
    private final Objective objective;
    @NotNull
    private final Action action;
    private final int amount;

    public TriggerCommand(@NotNull Objective objective) {
        this(objective, Action.ADD, 1);
    }

    public TriggerCommand(@NotNull Objective objective, @NotNull Action action, int amount) {
        if(!objective.getType().equals("trigger"))
            throw new IllegalArgumentException("Unable to use objective '" + objective.getName() + "' with TriggerCommand; Expected objective of type 'trigger', instead got '" + objective.getType() + "'");
        this.objective = objective;
        this.action = action;
        this.amount = amount;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "trigger " + objective.getName() + (action != Action.ADD || amount != 1 ? " " + action.toString().toLowerCase() + " " + amount : ""));
    }
}
