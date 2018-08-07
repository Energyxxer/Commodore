package com.energyxxer.commodore.score.access;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.score.MacroScore;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Structure which stores all the scoreboard accesses of all the entries of a {@link Function}, and gives each of them
 * a resolution based on their order: Used and Unused.<br>
 *     <ol>
 *         <li><b>Used: </b> This means that the scoreboard access is either of type Read, or that it is of type Write,
 *         and succeeded by another of type Read that accesses the same macro score holder.</li>
 *         <li><b>Unused: </b> This means that the scoreboard access is unused or redundant. Seen mainly on the first
 *         of two consecutive Write accesses on the same group of score holders.</li>
 *     </ol>
 *     For scoreboard accesses with dependencies, their resolution is based not on the previously stated rules, but
 *     rather it is made the same as the scoreboard access it depends on. For instance, a read access may be unused
 *     if it depends on a write access that's unused.
 * */
public class ScoreAccessLog {
    /**
     * The Function this access log is based on.
     * */
    private final Function parent;
    /**
     * Whether this access log has been analyzed and its scoreboard accesses resolved.
     * */
    private boolean resolved = false;
    /**
     * The ordered list of scoreboard accesses this log contains.
     * */
    private final ArrayList<ScoreboardAccess> log = new ArrayList<>();
    /**
     * The ordered list of scoreboard accesses whose resolution is Used.
     * */
    private ArrayList<ScoreboardAccess> finalAccesses = null;

    /**
     * Creates a score access log for the given function.
     *
     * @param parent The function for this access log.
     * */
    public ScoreAccessLog(Function parent) {
        this.parent = parent;
    }

    /**
     * Adds the given access to this log if it isn't already.
     *
     * @param access The access to add to this access log.
     * */
    public void filterAccess(ScoreboardAccess access) {
        if(!log.contains(access)) log.add(access);
    }

    /**
     * Processes the current log's accesses from end to beginning, giving each of them a resolution.
     * */
    public void resolve() {
        MacroScoreAccessLog macroLog = new MacroScoreAccessLog();

        for(int i = log.size() - 1; i >= 0; i--) {
            ScoreboardAccess access = log.get(i);
            Collection<ScoreboardAccess> dependencies = access.getDependencies();

            if(access.getResolution() != ScoreboardAccess.AccessResolution.UNRESOLVED) {
                if(access.getType() == ScoreboardAccess.AccessType.READ) {
                    macroLog.addUsed(access.getScores());
                } else if(access.getType() == ScoreboardAccess.AccessType.WRITE) {
                    macroLog.removeUsed(access.getScores());
                }
                continue;
            }

            if(!dependencies.isEmpty()) {
                for(ScoreboardAccess dependency : dependencies) {
                    if(dependency.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED) {
                        access.setResolution(ScoreboardAccess.AccessResolution.IN_PROCESS);

                        Function dependencyFunction = dependency.getFunction();
                        if(dependencyFunction == null)
                            throw new IllegalStateException("Dependency for access '" + access + " is not appended to a function");

                        dependencyFunction.getAccessLog().resolve();
                    }
                    if(dependency.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED)
                        throw new RuntimeException("wtf dependency unresolved after resolve called");
                    if(dependency.getResolution() == ScoreboardAccess.AccessResolution.IN_PROCESS)
                        throw new RuntimeException("wtf dependency in process after resolve called, cyclical function" +
                                "call what?");
                    if(dependency.getResolution() == ScoreboardAccess.AccessResolution.USED) access.setResolution(ScoreboardAccess.AccessResolution.USED);
                }
                if(access.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED) access.setResolution(ScoreboardAccess.AccessResolution.UNUSED);
                if(access.getType() == ScoreboardAccess.AccessType.READ && access.getResolution() == ScoreboardAccess.AccessResolution.USED) {
                    macroLog.addUsed(access.getScores());
                } else {
                    macroLog.removeUsed(access.getScores());
                }
            } else if(access.getType() == ScoreboardAccess.AccessType.WRITE) {
                if(macroLog.isLastFieldAccess(access.getScores()) || macroLog.areAnyUsed(access.getScores())) {
                    access.setResolution(ScoreboardAccess.AccessResolution.USED);
                } else access.setResolution(ScoreboardAccess.AccessResolution.UNUSED);
                macroLog.removeUsed(access.getScores());
            } else if(access.getType() == ScoreboardAccess.AccessType.READ) {
                access.setResolution(ScoreboardAccess.AccessResolution.USED);
                macroLog.addUsed(access.getScores());
            }
        }

        finalAccesses = new ArrayList<>();
        log.forEach(a -> {
            if(a.getResolution() == ScoreboardAccess.AccessResolution.USED) finalAccesses.add(a);
        });

        resolved = true;
    }

    /**
     * Retrieves the log's used scoreboard accesses. This method will resolve the log if it isn't already.
     *
     * @return The log's used scoreboard accesses.
     * */
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        if(!resolved) resolve();
        return finalAccesses;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ");
        sb.append(parent);
        sb.append(" : Access Log");
        sb.append(" --------------\n");
        log.forEach(a -> {
            sb.append(a);
            sb.append('\n');
        });
        sb.append("----------------------------------");
        return sb.toString();
    }
}

/**
 * Helper class that is used to keep track of macro scores that have been read by a scoreboard access so as to mark
 * previous write accesses as used or unused. Processing is done from the end to the beginning of the function.
 * */
class MacroScoreAccessLog {
    /**
     * Stores all macro scores that have been read and not written to.
     * */
    private final ArrayList<MacroScore> usedMacroScores = new ArrayList<>();
    /**
     * Stores all macro scores seen in the function.
     * */
    private final ArrayList<MacroScore> seenMacroScores = new ArrayList<>();

    /**
     * Marks the given macro scores as used, if they aren't already. Also adds the macro scores to the seen list.
     *
     * @param scores A collection of macro scores to add to this macro log.
     * */
    void addUsed(Collection<MacroScore> scores) {
        scores.forEach(s -> {
            boolean contained = false;
            for(MacroScore usedScore : usedMacroScores) {
                if(usedScore.matches(s)) {
                    contained = true;
                    break;
                }
            }
            if(!contained) usedMacroScores.add(s);
            contained = false;
            for(MacroScore seenScore : seenMacroScores) {
                if(seenScore.matches(s)) {
                    contained = true;
                    break;
                }
            }
            if(!contained) seenMacroScores.add(s);
        });
    }

    /**
     * Unmarks the given macro scores as used, if it is already. Also adds the macro scores to the seen list.
     *
     * @param scores A collection of macro scores to remove from this macro log.
     * */
    void removeUsed(Collection<MacroScore> scores) {
        for(MacroScore score : scores) {
            for(int i = 0; i < usedMacroScores.size(); i++) {
                MacroScore usedScore = usedMacroScores.get(i);
                if(usedScore.matches(score)) {
                    usedMacroScores.remove(i);
                    i--;
                }
            }
            boolean contained = false;
            for(MacroScore seenScore : seenMacroScores) {
                if(seenScore.matches(score)) {
                    contained = true;
                    break;
                }
            }
            if(!contained) seenMacroScores.add(score);
        }
    }

    /**
     * Checks if any of the given macro scores have had a read access later in the log and hasn't been overwritten by
     * another write access.
     *
     * @param scores A collection of scores to check whether they have been used or not.
     *
     * @return <code>true</code> if any one of the given scores has been read and not overwritten, <code>false</code>
     * otherwise.
     * */
    boolean areAnyUsed(Collection<MacroScore> scores) {
        for(MacroScore score : scores) {
            for(MacroScore usedScore : usedMacroScores) {
                if(usedScore.matches(score)) return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a given score has been present in this macro log before; that is, if it has been passed to
     * {@link MacroScoreAccessLog#addUsed(Collection)} or {@link MacroScoreAccessLog#removeUsed(Collection)} before.
     *
     * @param score The score to check if it has been seen.
     *
     * @return <code>true</code> if the macro score has been seen, <code>false</code> otherwise.
     * */
    private boolean isSeen(MacroScore score) {
        for(MacroScore seenScore : seenMacroScores) {
            if(seenScore.matches(score)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a list of all the macro scores used.
     *
     * @return The list of all the macro scores used.
     * */
    public Collection<MacroScore> getUsed() {
        return new ArrayList<>(usedMacroScores);
    }

    /**
     * Checks whether at least one from a given list of macro scores corresponds to a field objective.
     * */
    boolean isLastFieldAccess(Collection<MacroScore> scores) {
        for(MacroScore score : scores) {
            if(score.getObjective() != null && (score.getObjective().isField() && !isSeen(score))) return true;
        }
        return false;
    }
}