package com.energyxxer.commodore.score;

import com.energyxxer.commodore.score.access.ScoreAccessLog;

public class LocalScore {
    private Objective objective;
    private ScoreHolder holder;

    private ScoreAccessLog accessLog = new ScoreAccessLog(this);

    public LocalScore(Objective objective, ScoreHolder holder) {
        this.objective = objective;
        this.holder = holder;

        objective.getParent().registerLocalScore(this);
    }

    public Objective getObjective() {
        return objective;
    }

    public ScoreHolder getHolder() {
        return holder;
    }

    public ScoreAccessLog getAccessLog() {
        return accessLog;
    }

    @Override
    public String toString() {
        return "{" +
                "objective=" + objective +
                ", holder=" + holder +
                '}';
    }
}
