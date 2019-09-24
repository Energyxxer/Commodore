package com.energyxxer.commodore.functionlogic.commands.trigger;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class TriggerCommand implements Command {

    public static final String TRIGGER_CRITERION = "trigger";

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
        if(!objective.getType().equals(TRIGGER_CRITERION))
            throw new CommodoreException(CommodoreException.Source.TYPE_ERROR, "Unable to use objective '" + objective.getName() + "' with trigger; Expected objective of type '" + TRIGGER_CRITERION + "', instead got '" + objective.getType() + "'", objective, "OBJECTIVE");
        this.objective = objective;
        this.action = action;
        this.amount = amount;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "trigger " + objective.toString() + (action != Action.ADD || amount != 1 ? " " + action.toString().toLowerCase(Locale.ENGLISH) + " " + amount : ""));
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.trigger");
    }
}
