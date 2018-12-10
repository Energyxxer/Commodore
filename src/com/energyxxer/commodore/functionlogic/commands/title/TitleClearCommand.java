package com.energyxxer.commodore.functionlogic.commands.title;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class TitleClearCommand extends TitleCommand {

    public TitleClearCommand(@NotNull Entity player) {
        super(player);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "title \be0 clear", player);
    }
}
