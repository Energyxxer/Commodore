package com.energyxxer.commodore.commands.bossbar;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertBossbar;

public class BossbarRemoveCommand extends BossbarCommand {
    private final Type bossbar;

    public BossbarRemoveCommand(Type bossbar) {
        this.bossbar = bossbar;
        assertBossbar(bossbar);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "bossbar remove " + bossbar);
    }
}
