package com.energyxxer.commodore.functionlogic.commands.difficulty;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class DifficultyQueryCommand extends DifficultyCommand {

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "difficulty");
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
