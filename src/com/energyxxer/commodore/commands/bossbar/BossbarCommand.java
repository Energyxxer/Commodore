package com.energyxxer.commodore.commands.bossbar;

import com.energyxxer.commodore.commands.Command;

public abstract class BossbarCommand implements Command {
    public enum BossbarColor {
        BLUE, GREEN, PINK, PURPLE, RED, WHITE, YELLOW
    }

    public enum BossbarStyle {
        PROGRESS, NOTCHED_6, NOTCHED_10, NOTCHED_12, NOTCHED_20
    }
}
