package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.types.FunctionReference;

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
}
