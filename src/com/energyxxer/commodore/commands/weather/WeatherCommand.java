package com.energyxxer.commodore.commands.weather;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WeatherCommand implements Command {

    public static final int DEFAULT_DURATION = 300; //5 minutes

    public enum Mode {
        CLEAR, RAIN, THUNDER
    }

    private final Mode mode;
    private final int duration;

    public WeatherCommand(Mode mode) {
        this(mode, DEFAULT_DURATION);
    }

    public WeatherCommand(Mode mode, int duration) {
        this.mode = mode;
        this.duration = duration;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "weather " + mode.toString().toLowerCase() + (duration != DEFAULT_DURATION ? " " + duration : ""));
    }
}
