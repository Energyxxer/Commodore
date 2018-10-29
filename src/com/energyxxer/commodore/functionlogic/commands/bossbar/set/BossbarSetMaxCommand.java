package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarSetMaxCommand extends BossbarSetCommand {
    private int max;

    public BossbarSetMaxCommand(Type bossbar, int max) {
        super(bossbar);

        if(max < 1) throw new IllegalArgumentException("Max value should not be less than 1, found " + max);

        this.max = max;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "max " + max);
    }
}
