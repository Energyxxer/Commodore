package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.types.FunctionReference;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class FunctionCommand implements Command {
    private FunctionReference reference;

    public FunctionCommand(Function function) {
        this.reference = new FunctionReference(function);
    }

    public FunctionCommand(FunctionReference reference) {
        this.reference = reference;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "function " + reference;
    }

    //TODO: Resolve functions for each execution context

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        return reference.getFunction().getScoreboardAccesses();
    }
}
