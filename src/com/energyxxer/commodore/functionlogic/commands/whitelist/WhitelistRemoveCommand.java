package com.energyxxer.commodore.functionlogic.commands.whitelist;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class WhitelistRemoveCommand extends WhitelistChangeCommand {

    public WhitelistRemoveCommand(@NotNull Entity profile) {
        super(profile);
    }

    @Override
    protected String getSubCommand() {
        return "remove";
    }
}
