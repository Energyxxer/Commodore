package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

public class ScorePlayersOperation extends ScoreboardManipulation {

    public enum Operation {
        ADD("+="), SUBTRACT("-="), MULTIPLY("*="), DIVIDE("/="), MODULO("%="), LESS_THAN("<"), GREATER_THAN(">"), ASSIGN("=", false);

        private String shorthand;
        private boolean targetRead;

        Operation(String shorthand) {
            this(shorthand, true);
        }

        Operation(String shorthand, boolean targetRead) {
            this.shorthand = shorthand;
            this.targetRead = targetRead;
        }

        public String getShorthand() {
            return shorthand;
        }

        public boolean isTargetRead() {
            return targetRead;
        }
    }

    private LocalScore target;
    private Operation operation;
    private LocalScore source;

    public ScorePlayersOperation(LocalScore target, Operation operation, LocalScore source) {
        this.target = target;
        this.operation = operation;
        this.source = source;

        if (operation.isTargetRead()) {
            ScoreboardAccess access1 = new ScoreboardAccess(source, ScoreboardAccess.AccessType.READ);
            ScoreboardAccess access2 = new ScoreboardAccess(target, ScoreboardAccess.AccessType.READ, access1);
            ScoreboardAccess access3 = new ScoreboardAccess(target, ScoreboardAccess.AccessType.WRITE, access2);
            this.addAccesses(access1, access2, access3);
        } else {
            ScoreboardAccess access2 = new ScoreboardAccess(target, ScoreboardAccess.AccessType.WRITE);
            ScoreboardAccess access1 = new ScoreboardAccess(source, ScoreboardAccess.AccessType.READ, access2);
            this.addAccesses(access1, access2);
        }
    }

    @Override
    public String getOperationContent(Entity sender) {
        return "scoreboard players operation " +
                CommandUtils.getRawReference(target.getParent().getHolder(), sender) +
                " " +
                target.getObjective().getName() +
                " " +
                operation.getShorthand() +
                " " +
                CommandUtils.getRawReference(source.getParent().getHolder(), sender) +
                " " +
                source.getObjective().getName();
    }
}
