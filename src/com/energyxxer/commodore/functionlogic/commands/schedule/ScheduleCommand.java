package com.energyxxer.commodore.functionlogic.commands.schedule;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.functions.FunctionSection;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import com.energyxxer.commodore.util.TimeSpan;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertFunction;

public class ScheduleCommand implements Command {

    @NotNull
    private final Type function;

    @NotNull
    private final TimeSpan delay;

    public ScheduleCommand(@NotNull Function function, @NotNull TimeSpan delay) {
        this(new FunctionReference(function), delay);
    }

    public ScheduleCommand(@NotNull Type function, @NotNull TimeSpan delay) {
        this.function = function;
        this.delay = delay;

        assertFunction(function);

        if(delay.getTicks() == 0) throw new IllegalArgumentException("Cannot schedule for the same tick");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "schedule function " + function + " " + delay);
    }

    @Override
    public void onAppend(FunctionSection section, ExecutionContext execContext) {
        Command.super.onAppend(section, execContext);
    }
}
