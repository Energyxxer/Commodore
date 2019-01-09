package com.energyxxer.commodore.functionlogic.commands.kill;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KillCommand implements Command {

    @Nullable
    private final Entity entity;

    public KillCommand() {
        this(null);
    }

    public KillCommand(@Nullable Entity entity) {
        this.entity = entity;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return entity != null ? new CommandResolution(execContext, "kill " + entity) : new CommandResolution(execContext, "kill");
    }

}
