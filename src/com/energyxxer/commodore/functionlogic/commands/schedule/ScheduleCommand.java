package com.energyxxer.commodore.functionlogic.commands.schedule;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.functions.Function;
import com.energyxxer.commodore.functionlogic.functions.FunctionSection;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import com.energyxxer.commodore.util.TimeSpan;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static com.energyxxer.commodore.types.TypeAssert.assertFunction;

public class ScheduleCommand implements Command {

    public enum ScheduleMode {
        APPEND,
        REPLACE
    }

    @NotNull
    private final Type function;

    @NotNull
    private final TimeSpan delay;

    @NotNull
    private final ScheduleMode mode;

    public ScheduleCommand(@NotNull Function function, @NotNull TimeSpan delay) {
        this(function, delay, ScheduleMode.REPLACE);
    }

    public ScheduleCommand(@NotNull Function function, @NotNull TimeSpan delay, @NotNull ScheduleMode mode) {
        this(new FunctionReference(function), delay, mode);
    }

    public ScheduleCommand(@NotNull Type function, @NotNull TimeSpan delay) {
        this(function, delay, ScheduleMode.REPLACE);
    }

    public ScheduleCommand(@NotNull Type function, @NotNull TimeSpan delay, @NotNull ScheduleMode mode) {
        this.function = function;
        this.delay = delay;
        this.mode = mode;

        assertFunction(function);

        delay.assertNonNegative();
        if(delay.getTicks() <= 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Cannot schedule for the same tick", delay);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "schedule function " + function.toSafeString() + " " + delay + (mode == ScheduleMode.REPLACE ? "" : " " + mode.toString().toLowerCase(Locale.ENGLISH)));
    }

    @Override
    public void onAppend(FunctionSection section, ExecutionContext execContext) {
        Command.super.onAppend(section, execContext);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.schedule");
        if(mode != ScheduleMode.REPLACE) VersionFeatureManager.assertEnabled("command.schedule_function_append");
    }
}
