package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.commands.execute.SubCommandResult;
import com.energyxxer.commodore.entity.Entity;

import java.util.ArrayList;

public class ExecuteCommand implements Command {
    private Command chainedCommand;

    private ArrayList<ExecuteModifier> modifiers = new ArrayList<>();

    public ExecuteCommand(Command chainedCommand) {
        this.chainedCommand = chainedCommand;
    }

    public void addModifier(ExecuteModifier modifier) {
        this.modifiers.add(modifier);
    }

    @Override
    public String getRawCommand(Entity sender) {
        StringBuilder sb = new StringBuilder("execute ");

        for (ExecuteModifier modifier : modifiers) {
            SubCommandResult result = modifier.getSubCommand(sender);
            sb.append(result.getSubCommand());
            sb.append(' ');
            if (result.getNewSender() != null) sender = result.getNewSender();
        }
        sb.append("run ");
        sb.append(chainedCommand.getRawCommand(sender));
        return sb.toString();
    }
}
