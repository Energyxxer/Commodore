package com.energyxxer.commodore.score.access;

import com.energyxxer.commodore.commands.scoreboard.ScoreboardManipulation;
import com.energyxxer.commodore.functions.Function;

import java.util.ArrayList;

public class ScoreAccessLog {

    /*
    * TODO: Make this function-by-function, instead of local-score-by-local-score
    * */

    private final Function parent;

    private ArrayList<ScoreboardAccess> log = new ArrayList<>();

    public ScoreAccessLog(Function parent) {
        this.parent = parent;
    }

    public void filterManipulation(ScoreboardManipulation manipulation) {
        manipulation.getAccesses().forEach(this::filterAccess);
    }

    private void filterAccess(ScoreboardAccess access) {
        if(!log.contains(access)) log.add(access);
    }

    public void resolve() {
        boolean used = false;
        for(int i = log.size() - 1; i >= 0; i--) {
            ScoreboardAccess access = log.get(i);
            ScoreboardAccess dependency = access.getDependency();
            if(access.getResolution() != ScoreboardAccess.AccessResolution.UNRESOLVED) continue;
            if(dependency != null) {
                if(dependency.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED) {
                    access.setResolution(ScoreboardAccess.AccessResolution.IN_PROCESS);

                    Function dependencyFunction = dependency.getParent().getFunction();
                    if(dependencyFunction == null)
                        throw new IllegalStateException("Dependency for access '" + access + " is not appended to a function");

                    dependencyFunction.getAccessLog().resolve();
                }
                if(dependency.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED)
                    throw new RuntimeException("wtf dependency unresolved after resolve called");
                if(dependency.getResolution() == ScoreboardAccess.AccessResolution.IN_PROCESS)
                    throw new RuntimeException("wtf dependency in process after resolve called");
                access.setResolution(dependency.getResolution());
                used = access.getType() == ScoreboardAccess.AccessType.READ;
            } else if(access.getType() == ScoreboardAccess.AccessType.WRITE) {
                if(used) access.setResolution(ScoreboardAccess.AccessResolution.USED);
                else access.setResolution(ScoreboardAccess.AccessResolution.UNUSED);
                used = false;
            } else if(access.getType() == ScoreboardAccess.AccessType.READ) {
                access.setResolution(ScoreboardAccess.AccessResolution.USED);
                used = true;
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
