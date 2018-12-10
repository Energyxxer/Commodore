package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarSetValueCommand extends BossbarSetCommand {
    @NotNull
    private int value;

    public BossbarSetValueCommand(@NotNull Type bossbar, int value) {
        super(bossbar);

        if(value < 0) throw new IllegalArgumentException("Value should not be less than 0, found " + value);

        this.value = value;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "value " + value);
    }
}
