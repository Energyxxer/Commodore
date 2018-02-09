package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.types.FunctionReference;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class FunctionCommand implements Command {
    private FunctionReference reference;
    private ExecutionContext execContext = null;

    public FunctionCommand(Function function) {
        this.reference = new FunctionReference(function);
    }

    public FunctionCommand(FunctionReference reference) {
        this.reference = reference;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "function " + reference);
    }

    //TODO: Resolve functions for each execution context

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        if(execContext == null)
            throw new IllegalStateException("Cannot resolve scoreboard accesses for unappended function command");
        Collection<ScoreboardAccess> rv = reference.getFunction().getScoreboardAccesses(execContext);
        System.out.println("rv = " + rv);
        return rv;
    }

    @Override
    public void onAppend(Function function, ExecutionContext execContext) {
        this.execContext = execContext;
        Command.super.onAppend(function, execContext);
    }
}
