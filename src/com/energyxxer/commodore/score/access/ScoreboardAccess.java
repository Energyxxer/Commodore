package com.energyxxer.commodore.score.access;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.score.LocalScore;

public class ScoreboardAccess {

    private final LocalScore score;
    private final AccessType type;
    private final ScoreboardAccess dependency;
    private Function function;
    private AccessResolution resolution = AccessResolution.UNRESOLVED;

    public ScoreboardAccess(LocalScore score, AccessType type, ScoreboardAccess dependency) {
        this.score = score;
        this.type = type;
        this.dependency = dependency;

        //In theory this should never be an issue considering dependencies are final.
        if(dependency != null && dependency.getDependency() == this)
            throw new IllegalArgumentException("Cyclical scoreboard access dependency is not allowed. Also how tf did you do that? Report please.");
    }

    public ScoreboardAccess(LocalScore score, AccessType type) {
        this(score, type, null);
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public LocalScore getScore() {
        return score;
    }

    public AccessType getType() {
        return type;
    }

    public ScoreboardAccess getDependency() {
        return dependency;
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
        return "" + type + " " + score + ((dependency != null) ? "⫘ (" + dependency + ")" : "");
    }
}
