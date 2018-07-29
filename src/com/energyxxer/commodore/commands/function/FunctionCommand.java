package com.energyxxer.commodore.commands.function;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.functions.FunctionSection;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public class FunctionCommand implements Command {
    private final Type function;
    private ExecutionContext execContext = null;
    private Collection<ScoreboardAccess> accesses = null;

    public FunctionCommand(Function function) {
        this.function = new FunctionReference(function);
    }

    public FunctionCommand(FunctionReference function) {
        this.function = function;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "function " + function);
    }

    //TODO: Resolve functions for each execution context

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        if(execContext == null)
            throw new IllegalStateException("Cannot resolve scoreboard accesses for unappended function command");
        if(accesses != null) return accesses;
        if(function instanceof FunctionReference) {
            ((FunctionReference) function).getFunction().resolveAccessLogs();
            accesses = ((FunctionReference) function).getFunction().getScoreboardAccesses(execContext);
        }
        if(accesses == null) accesses = Collections.emptyList();
        return accesses;
    }

    @Override
    public void onAppend(FunctionSection section, ExecutionContext execContext) {
        this.execContext = execContext;
        Command.super.onAppend(section, execContext);
    }
}
