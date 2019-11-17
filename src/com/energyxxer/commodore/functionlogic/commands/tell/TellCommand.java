package com.energyxxer.commodore.functionlogic.commands.tell;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class TellCommand implements Command {
    @NotNull
    private final Entity player;
    @NotNull
    private final String message;

    public TellCommand(@NotNull Entity player, @NotNull String message) {
        this.player = player;
        this.message = message;

        player.assertPlayer();
        player.assertEntityFriendly();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "tell " + player + " " + message);
    }

}
