package com.energyxxer.commodore.functionlogic.commands.gamerule;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class GameruleQueryCommand extends GameruleCommand {

    private final String gamerule;

    public GameruleQueryCommand(String gamerule) {
        this.gamerule = gamerule;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "gamerule " + gamerule);
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
