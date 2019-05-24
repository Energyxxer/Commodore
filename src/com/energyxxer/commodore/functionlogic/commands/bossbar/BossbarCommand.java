package com.energyxxer.commodore.functionlogic.commands.bossbar;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;

public abstract class BossbarCommand implements Command {
    public enum BossbarColor {
        BLUE, GREEN, PINK, PURPLE, RED, WHITE, YELLOW
    }

    public enum BossbarStyle {
        PROGRESS, NOTCHED_6, NOTCHED_10, NOTCHED_12, NOTCHED_20
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("bossbars");
    }
}
