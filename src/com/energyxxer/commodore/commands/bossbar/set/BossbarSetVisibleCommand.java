package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarSetVisibleCommand extends BossbarSetCommand {
    private final boolean visible;

    public BossbarSetVisibleCommand(Type bossbar, boolean visible) {
        super(bossbar);
        this.visible = visible;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + visible);
    }
}
