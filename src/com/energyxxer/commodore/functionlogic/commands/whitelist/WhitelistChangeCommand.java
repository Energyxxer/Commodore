package com.energyxxer.commodore.functionlogic.commands.whitelist;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public abstract class WhitelistChangeCommand extends WhitelistCommand {

    @NotNull
    private final Entity profile;

    public WhitelistChangeCommand(@NotNull Entity profile) {
        this.profile = profile;
        profile.assertGameProfile();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "whitelist " + getSubCommand() + " " + profile.gameProfileToString());
    }

    protected abstract String getSubCommand();
}
