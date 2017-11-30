package com.energyxxer.commodore.score.operations;

import com.energyxxer.commodore.functions.Function;

public class ScoreGet extends ScoreHolderOperation {
    public ScoreGet(LocalScore score) {
        super(AccessType.READ, score);
    }

    @Override
    public String getOperationContent(Function function) {
        return "# use score";
    }
}
