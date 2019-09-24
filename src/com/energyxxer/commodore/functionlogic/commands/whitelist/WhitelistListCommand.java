package com.energyxxer.commodore.functionlogic.commands.whitelist;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WhitelistListCommand extends WhitelistCommand {
    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "whitelist list");
    }
}
