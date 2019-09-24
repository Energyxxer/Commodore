package com.energyxxer.commodore.functionlogic.commands.whitelist;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;

public abstract class WhitelistCommand implements Command {

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("server_commands");
    }
}
