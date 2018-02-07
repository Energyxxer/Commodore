package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class WeatherCommand implements Command {

    public static final int DEFAULT_DURATION = 300; //5 minutes

    public enum Mode {
        CLEAR, RAIN, THUNDER
    }

    private Mode mode;
    private int duration;

    public WeatherCommand(Mode mode) {
        this(mode, DEFAULT_DURATION);
    }

    public WeatherCommand(Mode mode, int duration) {
        this.mode = mode;
        this.duration = duration;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "weather " + mode.toString().toLowerCase() + (duration != DEFAULT_DURATION ? " " + duration : ""));
    }
}
