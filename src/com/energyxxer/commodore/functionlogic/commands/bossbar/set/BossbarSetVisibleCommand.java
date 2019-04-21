package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarSetVisibleCommand extends BossbarSetCommand {
    private final boolean visible;

    public BossbarSetVisibleCommand(@NotNull Type bossbar, boolean visible) {
        super(bossbar);
        this.visible = visible;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "visible " + visible);
    }
}
