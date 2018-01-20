package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ScorePlayersOperation implements Command {

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

    private ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

    public ScorePlayersOperation(LocalScore target, Operation operation, LocalScore source) {
        this.target = target;
        this.operation = operation;
        this.source = source;

        if(target.getHolder() instanceof Entity) {
            accesses.addAll(((Entity) target.getHolder()).getScoreboardAccesses());
        }
        if(source.getHolder() instanceof Entity) {
            accesses.addAll(((Entity) source.getHolder()).getScoreboardAccesses());
        }

        if(operation.isTargetRead()) {
            ScoreboardAccess access1 = new ScoreboardAccess(source.getMacroScores(), ScoreboardAccess.AccessType.READ);
            ScoreboardAccess access2 = new ScoreboardAccess(target.getMacroScores(), ScoreboardAccess.AccessType.READ, access1);
            ScoreboardAccess access3 = new ScoreboardAccess(target.getMacroScores(), ScoreboardAccess.AccessType.WRITE, access2);
            accesses.add(access1);
            accesses.add(access2);
            accesses.add(access3);
        } else {
            ScoreboardAccess access2 = new ScoreboardAccess(target.getMacroScores(), ScoreboardAccess.AccessType.WRITE);
            ScoreboardAccess access1 = new ScoreboardAccess(source.getMacroScores(), ScoreboardAccess.AccessType.READ, access2);
            accesses.add(access1);
            accesses.add(access2);
        }
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accesses;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "scoreboard players operation " +
                CommandUtils.getRawReference(target.getHolder(), sender) +
                " " +
                target.getObjective().getName() +
                " " +
                operation.getShorthand() +
                " " +
                CommandUtils.getRawReference(source.getHolder(), sender) +
                " " +
                source.getObjective().getName();
    }
}
