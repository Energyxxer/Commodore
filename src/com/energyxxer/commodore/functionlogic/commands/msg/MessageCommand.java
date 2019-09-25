package com.energyxxer.commodore.functionlogic.commands.msg;

import com.energyxxer.commodore.functionlogic.commands.tell.TellCommand;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class MessageCommand extends TellCommand {
    public MessageCommand(@NotNull Entity players, @NotNull String message) {
        super(players, message);
    }
}
