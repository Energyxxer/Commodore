package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarSetStyleCommand extends BossbarSetCommand {
    private final BossbarStyle style;

    public BossbarSetStyleCommand(Type bossbar, BossbarStyle style) {
        super(bossbar);
        this.style = style;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "style " + style.toString().toLowerCase());
    }
}
