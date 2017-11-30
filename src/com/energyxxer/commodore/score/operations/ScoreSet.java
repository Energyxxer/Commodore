package com.energyxxer.commodore.score.operations;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functions.Function;

public class ScoreSet extends ScoreHolderOperation {

    private int value;

    public ScoreSet(LocalScore score, int value) {
        super(AccessType.OVERWRITE, score);
        this.value = value;
    }

    @Override
    public String getOperationContent(Function function) {
        return "scoreboard players set " + CommandUtils.getRawReference(score.getParent().getHolder(), function.getSender()) + " " + score.getObjective().getName() + " " + value;
    }
}
