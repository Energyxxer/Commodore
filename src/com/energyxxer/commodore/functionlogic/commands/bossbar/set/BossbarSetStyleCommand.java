package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
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
