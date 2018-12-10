package com.energyxxer.commodore.functionlogic.commands.time;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class TimeQueryCommand extends TimeCommand {
    public enum TimeCounter {
        DAY, DAYTIME, GAMETIME
    }

    @NotNull
    private final TimeCounter counter;

    public TimeQueryCommand(@NotNull TimeCounter counter) {
        this.counter = counter;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "time query " + counter.toString().toLowerCase());
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
