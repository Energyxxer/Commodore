package com.energyxxer.commodore.functionlogic.commands.function;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertFunction;

public class FunctionCommand implements Command {
    @NotNull
    private final Type function;

    public FunctionCommand(@NotNull Function function) {
        this.function = new FunctionReference(function);
    }

    public FunctionCommand(@NotNull FunctionReference function) {
        this.function = function;
    }

    public FunctionCommand(@NotNull Type function) {
        this.function = function;
        assertFunction(function);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "function " + function.toSafeString());
    }
}
