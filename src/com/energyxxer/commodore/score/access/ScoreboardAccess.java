package com.energyxxer.commodore.score.access;

import com.energyxxer.commodore.commands.scoreboard.ScoreboardManipulation;
import com.energyxxer.commodore.score.LocalScore;

public class ScoreboardAccess {

    private final ScoreboardManipulation parent;
    private final LocalScore score;
    private final AccessType type;
    private final ScoreboardAccess dependency;
    private AccessResolution resolution = AccessResolution.UNRESOLVED;

    public ScoreboardAccess(ScoreboardManipulation parent, LocalScore score, AccessType type, ScoreboardAccess dependency) {
        this.parent = parent;
        this.score = score;
        this.type = type;
        this.dependency = dependency;

        //In theory this should never be an issue considering dependencies are final.
        if(dependency != null && dependency.getDependency() == this)
            throw new IllegalArgumentException("Cyclical scoreboard access dependency is not allowed. Also how tf did you do that? Report please.");
    }

    public ScoreboardAccess(ScoreboardManipulation parent, LocalScore score, AccessType type) {
        this(parent, score, type, null);
    }

    public ScoreboardManipulation getParent() {
        return parent;
    }

    public enum AccessResolution {
        UNRESOLVED, IN_PROCESS, USED, UNUSED
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

    public enum AccessType {
        READ, WRITE
    }

    @Override
    public String toString() {
        return "" + type + " " + score + ((dependency != null) ? "⫘ (" + dependency + ")" : "");
    }
}
