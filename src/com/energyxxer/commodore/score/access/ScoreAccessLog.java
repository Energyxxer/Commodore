package com.energyxxer.commodore.score.access;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.MacroScore;

import java.util.ArrayList;

public class ScoreAccessLog {

    private final Function parent;

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
            ScoreboardAccess dependency = access.getDependency();

            if(access.getResolution() != ScoreboardAccess.AccessResolution.UNRESOLVED) continue;

            if(dependency != null) {
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
                access.setResolution(dependency.getResolution());
                if(access.getType() == ScoreboardAccess.AccessType.READ && access.getResolution() == ScoreboardAccess.AccessResolution.USED) {
                    macroLog.addUsed(access.getScore());
                } else {
                    macroLog.removeUsed(access.getScore());
                }
            } else if(access.getType() == ScoreboardAccess.AccessType.WRITE) {
                if(macroLog.isUsed(access.getScore())) access.setResolution(ScoreboardAccess.AccessResolution.USED);
                else access.setResolution(ScoreboardAccess.AccessResolution.UNUSED);
                macroLog.removeUsed(access.getScore());
            } else if(access.getType() == ScoreboardAccess.AccessType.READ) {
                access.setResolution(ScoreboardAccess.AccessResolution.USED);
                macroLog.addUsed(access.getScore());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ");
        sb.append(parent);
        sb.append(" : Access Log");
        sb.append(" --------------\n");
        log.forEach(a -> {
            sb.append(a.getType());
            sb.append(" ");
            if(a.getDependency() != null) {
                sb.append("⫘ (");
                sb.append(a.getDependency());
                sb.append(") ");
            }
            sb.append("- ");
            sb.append(a.getResolution());
            sb.append('\n');
        });
        sb.append("----------------------------------");
        return sb.toString();
    }

}

class MacroScoreAccessLog {
    ArrayList<MacroScore> usedMacroScores = new ArrayList<>();

    void addUsed(LocalScore score) {
        score.getMacroScores().forEach(s -> {
            if(!usedMacroScores.contains(s)) usedMacroScores.add(s);
        });
    }

    void removeUsed(LocalScore score) {
        usedMacroScores.removeAll(score.getMacroScores());
    }

    boolean isUsed(LocalScore score) {
        for(MacroScore macro : score.getMacroScores()) {
            if(usedMacroScores.contains(macro)) return true;
        }
        return false;
    }
}