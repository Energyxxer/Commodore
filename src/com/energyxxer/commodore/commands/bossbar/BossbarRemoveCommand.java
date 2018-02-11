package com.energyxxer.commodore.commands.bossbar;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.BossbarReference;
import org.jetbrains.annotations.NotNull;

public class BossbarRemoveCommand extends BossbarCommand {
    private final BossbarReference reference;

    public BossbarRemoveCommand(BossbarReference reference) {
        this.reference = reference;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "bossbar remove " + reference);
    }
}
