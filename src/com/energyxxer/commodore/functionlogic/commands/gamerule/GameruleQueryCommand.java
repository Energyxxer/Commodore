package com.energyxxer.commodore.functionlogic.commands.gamerule;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertGamerule;

public class GameruleQueryCommand extends GameruleCommand {

    @NotNull
    private final Type gamerule;

    public GameruleQueryCommand(@NotNull Type gamerule) {
        this.gamerule = gamerule;
        assertGamerule(gamerule);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "gamerule " + gamerule);
    }

}
