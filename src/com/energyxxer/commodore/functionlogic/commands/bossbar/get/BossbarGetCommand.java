package com.energyxxer.commodore.functionlogic.commands.bossbar.get;

import com.energyxxer.commodore.functionlogic.commands.bossbar.BossbarCommand;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertBossbar;

public abstract class BossbarGetCommand extends BossbarCommand {
    @NotNull
    protected final Type bossbar;

    public BossbarGetCommand(@NotNull Type bossbar) {
        this.bossbar = bossbar;

        assertBossbar(bossbar);
    }

    protected abstract @NotNull String getKeyword();

    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "bossbar get " + bossbar + " " + getKeyword());
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
