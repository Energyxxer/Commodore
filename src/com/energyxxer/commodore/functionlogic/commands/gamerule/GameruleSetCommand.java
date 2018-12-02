package com.energyxxer.commodore.functionlogic.commands.gamerule;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertGamerule;

public class GameruleSetCommand extends GameruleCommand {

    private final Type gamerule;
    private final String value;

    public GameruleSetCommand(Type gamerule, Object value) {
        this.gamerule = gamerule;
        this.value = String.valueOf(value);

        assertGamerule(gamerule);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "gamerule " +gamerule + " " + value);
    }
}
