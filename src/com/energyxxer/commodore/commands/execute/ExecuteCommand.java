package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ExecuteCommand implements Command {

    private final Command chainedCommand;
    private final ArrayList<ExecuteModifier> modifiers = new ArrayList<>();

    public ExecuteCommand(Command chainedCommand) {
        this.chainedCommand = chainedCommand;
    }

    public void addModifier(ExecuteModifier modifier) {
        this.modifiers.add(modifier);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        ExecutionContext chainedContext = new ExecutionContext(execContext.getFinalSender(), this.modifiers);
        return chainedCommand.resolveCommand(chainedContext);
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        ArrayList<ScoreboardAccess> accesses = new ArrayList<>();
        for(ExecuteModifier modifier : modifiers) {
            accesses.addAll(modifier.getScoreboardAccesses());
        }
        accesses.addAll(chainedCommand.getScoreboardAccesses());
        return accesses;
    }

    @Override
    public boolean isUsed() {
        return Command.super.isUsed() && chainedCommand.isUsed();
    }

    @Override
    public void onAppend(Function function, ExecutionContext execContext) {
        Command.super.onAppend(function, execContext);
        chainedCommand.onAppend(function, new ExecutionContext(execContext.getFinalSender(), this.modifiers));
    }
}
