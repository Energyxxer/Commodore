package com.energyxxer.commodore.functionlogic.commands.time;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.util.TimeSpan;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class TimeAddCommand extends TimeCommand {
    @NotNull
    private final TimeSpan time;

    public TimeAddCommand(@NotNull TimeSpan time) {
        this.time = time;

        time.assertNonNegative();
        if(time.amount < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Time must be non-negative, found " + time, time, "TIME");
    }
    public TimeAddCommand(int time) {
        this(new TimeSpan(time));
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        if(VersionFeatureManager.getBoolean("command.time.units")) {
            return new CommandResolution(execContext, "time add " + time);
        } else {
            return new CommandResolution(execContext, "time add " + ((int)time.amount * time.units.ticksInUnit));
        }
    }
}
