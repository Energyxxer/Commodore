package com.energyxxer.commodore.functionlogic.commands.weather;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class WeatherCommand implements Command {

    public static final int DEFAULT_DURATION = 300; //5 minutes

    public enum Mode {
        CLEAR, RAIN, THUNDER
    }

    @NotNull
    private final Mode mode;
    private final int duration;

    public WeatherCommand(@NotNull Mode mode) {
        this(mode, DEFAULT_DURATION);
    }

    public WeatherCommand(@NotNull Mode mode, int duration) {
        this.mode = mode;
        this.duration = duration;

        if(duration < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Duration must not be less than 0, found " + duration, duration, "DURATION");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "weather " + mode.toString().toLowerCase(Locale.ENGLISH) + (duration != DEFAULT_DURATION ? " " + duration : ""));
    }
}
