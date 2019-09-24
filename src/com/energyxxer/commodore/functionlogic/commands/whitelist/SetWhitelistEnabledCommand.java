package com.energyxxer.commodore.functionlogic.commands.whitelist;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class SetWhitelistEnabledCommand extends WhitelistCommand {
    private final boolean enable;

    public SetWhitelistEnabledCommand(boolean enable) {
        this.enable = enable;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "whitelist " + (enable ? "on" : "off"));
    }
}
