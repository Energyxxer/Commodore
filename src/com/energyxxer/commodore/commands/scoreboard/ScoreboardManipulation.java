package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public abstract class ScoreboardManipulation implements Command {

    private ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

    protected ScoreboardManipulation(ScoreboardAccess... accesses) {
        this.addAccesses(accesses);
    }

    public ScoreboardManipulation(Collection<ScoreboardAccess> accesses) {
        this.addAccesses(accesses);
    }

    protected void addAccesses(ScoreboardAccess... accesses) {
        this.addAccesses(Arrays.asList(accesses));
    }

    protected void addAccesses(Collection<ScoreboardAccess> accesses) {
        this.accesses.addAll(accesses);
    }

    public ArrayList<ScoreboardAccess> getAccesses() {
        return accesses;
    }

    public boolean isUsed() {
        for (ScoreboardAccess access : accesses) {
            if (access.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED)
                throw new IllegalStateException("This ScoreboardManipulation has unresolved access: " + this);
            if (access.getResolution() == ScoreboardAccess.AccessResolution.UNUSED) return false;
        }
        return true;
    }

    public abstract String getOperationContent(Entity sender);

    @Override
    public String getRawCommand(Entity sender) {
        return (isUsed()) ? getOperationContent(sender) : "# [ REMOVED ]";
    }

    @Override
    public void onAppend() {
        accesses.forEach(a -> a.getScore().getAccessLog().filterManipulation(this));
    }
}
