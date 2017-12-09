package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.functions.Function;

public class FunctionCommand implements Command {
    private Function function;

    public FunctionCommand(Function function) {
        this.function = function;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "function " + function.getName();
    }
}
