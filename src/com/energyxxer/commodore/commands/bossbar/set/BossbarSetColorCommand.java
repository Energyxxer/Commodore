package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.commands.bossbar.BossbarCommand;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarSetColorCommand extends BossbarSetCommand {
    private final BossbarCommand.BossbarColor color;

    public BossbarSetColorCommand(Type bossbar, BossbarCommand.BossbarColor color) {
        super(bossbar);
        this.color = color;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "color " + color.toString().toLowerCase());
    }
}
