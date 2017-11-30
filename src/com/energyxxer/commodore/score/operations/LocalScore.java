package com.energyxxer.commodore.score.operations;

import com.energyxxer.commodore.score.Objective;
import com.energyxxer.commodore.score.ScoreManager;

import java.util.ArrayList;

public class LocalScore {
    private Objective objective;
    private ScoreManager parent;

    private ArrayList<ScoreHolderOperation> operationBuffer = new ArrayList<>();

    public LocalScore(Objective objective, ScoreManager parent) {
        this.objective = objective;
        this.parent = parent;
    }

    void filter(ScoreHolderOperation operation) {
        if(operation.getAccess() == ScoreHolderOperation.AccessType.READ) {
            operationBuffer.forEach(o -> o.setUsed(true));
            operationBuffer.clear();
        } else if(operation.getAccess() == ScoreHolderOperation.AccessType.OVERWRITE) {
            operationBuffer.clear();
            operationBuffer.add(operation);
        } else if(operation.getAccess() == ScoreHolderOperation.AccessType.ADJUST) {
            operationBuffer.add(operation);
        }
    }

    public Objective getObjective() {
        return objective;
    }

    public ScoreManager getParent() {
        return parent;
    }
}
