package com.energyxxer.commodore.functionlogic.commands.schedule;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertFunction;

public class ScheduleClearCommand implements Command {

    @NotNull
    private final Type function;

    public ScheduleClearCommand(@NotNull Function function) {
        this(new FunctionReference(function));
    }

    public ScheduleClearCommand(@NotNull Type function) {
        this.function = function;

        assertFunction(function);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "schedule clear " + function.toSafeString());
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.schedule");
        VersionFeatureManager.assertEnabled("command.schedule_clear");
    }
}
