package com.energyxxer.commodore.functionlogic.commands.experience;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class ExperienceQueryCommand extends ExperienceCommand {

    @NotNull
    private final Unit unit;

    public ExperienceQueryCommand(@Nullable Entity player, @NotNull Unit unit) {
        super(player);
        this.unit = unit;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "xp query " + player + " " + unit.toString().toLowerCase(Locale.ENGLISH));
    }

    @Override
    public void assertAvailable() {
        super.assertAvailable();
        VersionFeatureManager.assertEnabled("command.xp.explicit");
    }
}
