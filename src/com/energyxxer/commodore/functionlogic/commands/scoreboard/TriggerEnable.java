package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.commands.trigger.TriggerCommand;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class TriggerEnable implements Command {
    @NotNull
    private final Entity player;
    @NotNull
    private final Objective objective;

    public TriggerEnable(@NotNull Entity player, @NotNull Objective objective) {
        this.player = player;
        this.objective = objective;

        if(!objective.getType().equals(TriggerCommand.TRIGGER_CRITERION)) throw new CommodoreException(CommodoreException.Source.TYPE_ERROR, "Unable to use objective '" + objective.toString() + "' with trigger enable; Expected objective of type '" + TriggerCommand.TRIGGER_CRITERION + "', instead got '" + objective.getType() + "'", objective, "OBJECTIVE");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players enable " + player + " " + objective.toString());
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.trigger");
    }
}
