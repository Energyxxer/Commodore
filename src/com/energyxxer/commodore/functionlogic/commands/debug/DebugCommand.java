package com.energyxxer.commodore.functionlogic.commands.debug;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;

public abstract class DebugCommand implements Command {
    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.debug");
    }
}
