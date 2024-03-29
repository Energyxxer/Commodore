package com.energyxxer.commodore.functionlogic.commands.locate;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertStructure;

public class LocateCommand implements Command {
    @NotNull
    private final Type structure;

    public LocateCommand(@NotNull Type structure) {
        this.structure = structure;

        assertStructure(structure);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, (VersionFeatureManager.getBoolean("command.locate.merge", false) ? "locate structure " : "locate ") + structure.toSafeString());
    }

}
