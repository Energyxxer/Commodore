package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.defaults.BossbarReference;
import org.jetbrains.annotations.NotNull;

public class BossbarSetStyleCommand extends BossbarSetCommand {
    private final BossbarStyle style;

    public BossbarSetStyleCommand(BossbarReference reference, BossbarStyle style) {
        super(reference);
        this.style = style;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "style " + style.toString().toLowerCase());
    }
}
