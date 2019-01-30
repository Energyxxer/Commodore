package com.energyxxer.commodore.functionlogic.commands.msg;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class MessageCommand implements Command {
    @NotNull
    private final Entity players;
    @NotNull
    private final String message;

    public MessageCommand(@NotNull Entity players, @NotNull String message) {
        this.players = players;
        this.message = message;

        players.assertPlayer();
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "msg " + players + " " + message);
    }
}
