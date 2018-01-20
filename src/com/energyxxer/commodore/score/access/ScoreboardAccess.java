package com.energyxxer.commodore.score.access;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.score.MacroScore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ScoreboardAccess {

    private final Collection<MacroScore> scores;
    private final AccessType type;
    private final Collection<ScoreboardAccess> dependencies;
    private Function function;
    private AccessResolution resolution = AccessResolution.UNRESOLVED;

    public ScoreboardAccess(Collection<MacroScore> scores, AccessType type, ScoreboardAccess... dependencies) {
        this(scores, type, Arrays.asList(dependencies));
    }

    public ScoreboardAccess(Collection<MacroScore> scores, AccessType type, Collection<ScoreboardAccess> dependencies) {
        this.scores = new ArrayList<>(scores);
        this.type = type;
        this.dependencies = (dependencies != null) ? new ArrayList<>(dependencies) : Collections.emptyList();
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Collection<MacroScore> getScores() {
        return scores;
    }

    public AccessType getType() {
        return type;
    }

    public Collection<ScoreboardAccess> getDependencies() {
        return dependencies;
    }

    public AccessResolution getResolution() {
        return resolution;
    }

    void setResolution(AccessResolution resolution) {
        this.resolution = resolution;
    }

    public enum AccessResolution {
        UNRESOLVED, IN_PROCESS, USED, UNUSED
    }

    public enum AccessType {
        READ, WRITE
    }

    @Override
    public String toString() {
        return "" + type + " " + scores + ((dependencies != null) ? " ⫘ (" + dependencies + ")" : "") + " - " + resolution;
    }
}
