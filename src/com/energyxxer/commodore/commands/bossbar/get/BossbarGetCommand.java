package com.energyxxer.commodore.commands.bossbar.get;

import com.energyxxer.commodore.commands.bossbar.BossbarCommand;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertBossbar;

public abstract class BossbarGetCommand extends BossbarCommand {
    protected final Type bossbar;

    public BossbarGetCommand(Type bossbar) {
        this.bossbar = bossbar;

        assertBossbar(bossbar);
    }

    protected abstract String getKeyword();

    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "bossbar get " + bossbar + " " + getKeyword());
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
