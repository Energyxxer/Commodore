package com.energyxxer.commodore.score.operations;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.functions.FunctionWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public abstract class ScoreboardManipulation implements FunctionWriter {

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
        for(ScoreboardAccess access : accesses) {
            if(access.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED) throw new IllegalStateException("This ScoreboardManipulation has unresolved access: " + this);
            if(access.getResolution() == ScoreboardAccess.AccessResolution.UNUSED) return false;
        }
        return true;
    }

    public abstract String getOperationContent(Function function);

    @Override
    public String toFunctionContent(Function function) {
        return (isUsed()) ? getOperationContent(function) : "# [ REMOVED ]";
    }

    @Override
    public void onAppend() {
        accesses.forEach(a -> a.getScore().getAccessLog().filterManipulation(this));
    }
}
