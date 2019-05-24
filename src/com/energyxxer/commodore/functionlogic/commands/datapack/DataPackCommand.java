package com.energyxxer.commodore.functionlogic.commands.datapack;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;

public abstract class DataPackCommand implements Command {
    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("data_packs");
    }
}
