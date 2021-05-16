package com.energyxxer.commodore.functionlogic.commands.debug;

import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertFunction;

public class DebugFunctionCommand extends DebugCommand {
    @NotNull
    private final Type function;

    public DebugFunctionCommand(@NotNull Function function) {
        this.function = new FunctionReference(function);
    }

    public DebugFunctionCommand(@NotNull FunctionReference function) {
        this.function = function;
    }

    public DebugFunctionCommand(@NotNull Type function) {
        this.function = function;
        assertFunction(function);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "debug function " + function.toSafeString());
    }

    @Override
    public void assertAvailable() {
        super.assertAvailable();
        VersionFeatureManager.assertEnabled("command.debug.function");
    }
}
