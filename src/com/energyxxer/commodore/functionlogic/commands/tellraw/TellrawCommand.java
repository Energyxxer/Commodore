package com.energyxxer.commodore.functionlogic.commands.tellraw;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.textcomponents.TextComponent;
import org.jetbrains.annotations.NotNull;

public class TellrawCommand implements Command {
    @NotNull
    private final Entity player;
    @NotNull
    private final TextComponent message;

    public TellrawCommand(@NotNull Entity player, @NotNull TextComponent message) {
        this.player = player;
        this.message = message;

        player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        String raw = message.toString();
        return new CommandResolution(execContext, "tellraw " + player + " " + raw);
    }

    @Override
    public void assertAvailable() {
        player.assertAvailable();
    }
}
