package com.energyxxer.commodore.functionlogic.commands.gamerule;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertGamerule;

public class GameruleSetCommand extends GameruleCommand {
    @NotNull
    private final Type gamerule;
    @NotNull
    private final String value;

    public GameruleSetCommand(@NotNull Type gamerule, @NotNull Object value) {
        this.gamerule = gamerule;
        this.value = String.valueOf(value);

        assertGamerule(gamerule);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "gamerule " + gamerule.toSafeString() + " " + value);
    }
}
