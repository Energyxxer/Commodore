package com.energyxxer.commodore.functionlogic.commands.experience;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class ExperienceAddCommand extends ExperienceCommand {

    private final int amount;
    @NotNull
    private final Unit unit;

    public ExperienceAddCommand(@Nullable Entity player, int amount, @NotNull Unit unit) {
        super(player);
        this.amount = amount;
        this.unit = unit;

        if(!VersionFeatureManager.getBoolean("command.xp.explicit", true) && unit == Unit.POINTS && amount < 0) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Cannot give the player negative experience points", amount, "AMOUNT");
        }
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        if(VersionFeatureManager.getBoolean("command.xp.explicit", true)) {
            return new CommandResolution(execContext, "xp add " + player + " " + amount + " " + unit.toString().toLowerCase(Locale.ENGLISH));
        } else {
            return new CommandResolution(execContext, "xp " + amount + (unit == Unit.LEVELS ? "L" : "") + " " + player);
        }
    }
}
