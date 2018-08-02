package com.energyxxer.commodore.commands.locate;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertStructure;

public class LocateCommand implements Command {
    private final Type structure;

    public LocateCommand(Type structure) {
        this.structure = structure;

        assertStructure(structure);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "locate " + structure);
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
