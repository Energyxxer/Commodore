package com.energyxxer.commodore.score.access;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.score.MacroScore;
import com.energyxxer.commodore.score.Objective;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreAccessLog {

    private final Function parent;
    private boolean resolved = false;
    private ArrayList<ScoreboardAccess> finalAccesses = null;
    private final ArrayList<ScoreboardAccess> log = new ArrayList<>();

    public ScoreAccessLog(Function parent) {
        this.parent = parent;
    }

    public void filterAccess(ScoreboardAccess access) {
        if(!log.contains(access)) log.add(access);
    }

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
                        throw new RuntimeException("wtf dependency in process after resolve called");
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

class MacroScoreAccessLog {
    private final ArrayList<MacroScore> usedMacroScores = new ArrayList<>();
    private final ArrayList<Objective> seenObjectives = new ArrayList<>();

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
            if(s.getObjective() != null && !seenObjectives.contains(s.getObjective())) seenObjectives.add(s.getObjective());
        });
    }

    void removeUsed(Collection<MacroScore> scores) {
        for(MacroScore score : scores) {
            for(int i = 0; i < usedMacroScores.size(); i++) {
                MacroScore usedScore = usedMacroScores.get(i);
                if(usedScore.matches(score)) {
                    usedMacroScores.remove(i);
                    i--;
                }
            }
        }
    }

    boolean areAnyUsed(Collection<MacroScore> scores) {
        for(MacroScore score : scores) {
            for(MacroScore usedScore : usedMacroScores) {
                if(usedScore.matches(score)) return true;
            }
        }
        return false;
    }

    public Collection<MacroScore> getUsed() {
        return new ArrayList<>(usedMacroScores);
    }

    boolean isLastFieldAccess(Collection<MacroScore> scores) {
        for(MacroScore score : scores) {
            if(score.getObjective() != null && (!score.getObjective().isField() || seenObjectives.contains(score.getObjective()))) return false;
        }
        return true;
    }
}