package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.functionlogic.commands.bossbar.BossbarCommand;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class BossbarSetColorCommand extends BossbarSetCommand {
    private final @NotNull BossbarCommand.BossbarColor color;

    public BossbarSetColorCommand(@NotNull Type bossbar, @NotNull BossbarCommand.BossbarColor color) {
        super(bossbar);
        this.color = color;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "color " + color.toString().toLowerCase(Locale.ENGLISH));
    }
}
