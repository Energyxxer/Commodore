package com.energyxxer.commodore.functionlogic.commands.clone;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class CloneCommand implements Command {

    public enum SourceMode {
        NORMAL, FORCE, MOVE;

        public static final SourceMode DEFAULT = NORMAL;
    }

    @NotNull
    private final CoordinateSet source1;
    @NotNull
    private final CoordinateSet source2;

    @NotNull
    protected final CoordinateSet destination;

    @NotNull
    private final SourceMode sourceMode;

    public CloneCommand(CoordinateSet source1, CoordinateSet source2, CoordinateSet destination) {
        this(source1, source2, destination, SourceMode.DEFAULT);
    }

    public CloneCommand(@NotNull CoordinateSet source1, @NotNull CoordinateSet source2, @NotNull CoordinateSet destination, @NotNull SourceMode sourceMode) {
        this.source1 = source1;
        this.source2 = source2;
        this.destination = destination;
        this.sourceMode = sourceMode;
    }

    @NotNull
    private String getBase() {
        return "clone " + source1.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + source2.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + destination.getAs(Coordinate.DisplayMode.BLOCK_POS);
    }

    @NotNull
    protected String getMaskExtra() {
        return (sourceMode != SourceMode.DEFAULT) ? " replace" : "";
    }

    @NotNull
    private String getSourceModeExtra() {
        return (sourceMode != SourceMode.DEFAULT) ? " " + sourceMode.toString().toLowerCase(Locale.ENGLISH) : "";
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + getMaskExtra() + getSourceModeExtra());
    }
}
