package com.energyxxer.commodore.functionlogic.commands.tag;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class TagQueryCommand implements Command {

    @NotNull
    private final Entity entity;

    public TagQueryCommand(@NotNull Entity entity) {
        this.entity = entity;
        entity.assertEntityFriendly();
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "tag " + entity + " list");
    }

    @Override
    public void assertAvailable() {
        entity.assertAvailable();
    }
}
