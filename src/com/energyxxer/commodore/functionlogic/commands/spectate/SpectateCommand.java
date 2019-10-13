package com.energyxxer.commodore.functionlogic.commands.spectate;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;

public abstract class SpectateCommand implements Command {
    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.spectate");
    }
}
