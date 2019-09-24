package com.energyxxer.commodore.functionlogic.commands.setidletimeout;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class SetIdleTimeoutCommand implements Command {
    private final int minutes;

    public SetIdleTimeoutCommand(int minutes) {
        this.minutes = minutes;

        if(minutes < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Idle timeout must be non-negative, found " + minutes, minutes, "MINUTES");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "setidletimeout " + minutes);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("server_commands");
    }
}
