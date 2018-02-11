package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.BossbarReference;
import org.jetbrains.annotations.NotNull;

public class BossbarSetValueCommand extends BossbarSetCommand {
    private int value;

    public BossbarSetValueCommand(BossbarReference reference, int value) {
        super(reference);

        if(value < 0) throw new IllegalArgumentException("Value should not be less than 0, found " + value);

        this.value = value;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "value " + value);
    }
}
