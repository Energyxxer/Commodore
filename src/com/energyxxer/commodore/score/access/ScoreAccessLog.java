package com.energyxxer.commodore.score.access;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.score.MacroScore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ScoreAccessLog {

    private final Function parent;
    private boolean resolved = false;
    private Collection<ScoreboardAccess> preAccesses = null;
    private ArrayList<ScoreboardAccess> log = new ArrayList<>();

    public ScoreAccessLog(Function parent) {
        this.parent = parent;
    }

    public void filterCommand(Command command) {
        command.getScoreboardAccesses().forEach(this::filterAccess);
    }

    public void filterAccess(ScoreboardAccess access) {
        if(!log.contains(access)) log.add(access);
    }

    public void resolve() {

        MacroScoreAccessLog macroLog = new MacroScoreAccessLog();

        for(int i = log.size() - 1; i >= 0; i--) {
            ScoreboardAccess access = log.get(i);
            Collection<ScoreboardAccess> dependencies = access.getDependencies();

            if(access.getResolution() != ScoreboardAccess.AccessResolution.UNRESOLVED) continue;

            if(dependencies.size() > 0) {
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
                if(macroLog.areAnyUsed(access.getScores())) access.setResolution(ScoreboardAccess.AccessResolution.USED);
                else access.setResolution(ScoreboardAccess.AccessResolution.UNUSED);
                macroLog.removeUsed(access.getScores());
            } else if(access.getType() == ScoreboardAccess.AccessType.READ) {
                access.setResolution(ScoreboardAccess.AccessResolution.USED);
                macroLog.addUsed(access.getScores());
            }
        }

        preAccesses = Collections.singletonList(new ScoreboardAccess(macroLog.getUsed(), ScoreboardAccess.AccessType.READ));

        resolved = true;
    }

    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        if(!resolved) resolve();
        return preAccesses;
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
    private ArrayList<MacroScore> usedMacroScores = new ArrayList<>();

    void addUsed(Collection<MacroScore> scores) {
        scores.forEach(s -> {
            if(!usedMacroScores.contains(s)) usedMacroScores.add(s);
        });
    }

    void removeUsed(Collection<MacroScore> scores) {
        usedMacroScores.removeAll(scores);
    }

    boolean areAnyUsed(Collection<MacroScore> scores) {
        for(MacroScore score : scores) {
            if(usedMacroScores.contains(score)) return true;
        }
        return false;
    }

    public Collection<MacroScore> getUsed() {
        return new ArrayList<>(usedMacroScores);
    }
}