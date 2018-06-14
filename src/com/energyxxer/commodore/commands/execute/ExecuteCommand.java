package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.functions.FunctionSection;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ExecuteCommand implements Command {

    private final Command chainedCommand;
    private final ArrayList<ExecuteModifier> modifiers = new ArrayList<>();

    public ExecuteCommand(@NotNull Command chainedCommand) {
        this(chainedCommand, Collections.emptyList());
    }

    public ExecuteCommand(@NotNull Command chainedCommand, @NotNull ExecuteModifier... modifiers) {
        this(chainedCommand, Arrays.asList(modifiers));
    }

    public ExecuteCommand(@NotNull Command chainedCommand, @NotNull Collection<ExecuteModifier> modifiers) {
        this.chainedCommand = chainedCommand;
        this.modifiers.addAll(modifiers);
    }

    public void addModifier(@NotNull ExecuteModifier modifier) {
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
    public void onAppend(@NotNull FunctionSection section, @NotNull ExecutionContext execContext) {
        Command.super.onAppend(section, execContext);
        chainedCommand.onAppend(section, new ExecutionContext(execContext.getFinalSender(), this.modifiers));
    }

    @Override
    public boolean isScoreboardManipulation() {
        return chainedCommand.isScoreboardManipulation();
    }
}
