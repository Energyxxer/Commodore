package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import org.jetbrains.annotations.NotNull;

public class ScorePlayersOperation implements Command {

    public enum Operation {
        ADD("+="),
        SUBTRACT("-="),
        MULTIPLY("*="),
        DIVIDE("/="),
        MODULO("%="),
        LESS_THAN("<"),
        GREATER_THAN(">"),
        ASSIGN("="),
        SWAP("><");
        //Leftmost 2 bits are for the read-access of target and source respectively.
        //Rightmost 2 bits are for write-access of target and source respectively.

        @NotNull
        private final String shorthand;

        Operation(@NotNull String shorthand) {
            this.shorthand = shorthand;
        }

        @NotNull
        public String getShorthand() {
            return shorthand;
        }

        public static Operation getOperationForSymbol(String symbol) {
            for(Operation value : values()) {
                if(value.shorthand.equals(symbol)) return value;
            }
            return null;
        }
    }

    @NotNull
    private final LocalScore target;
    @NotNull
    private final Operation operation;
    @NotNull
    private final LocalScore source;

    public ScorePlayersOperation(@NotNull LocalScore target, @NotNull Operation operation, @NotNull LocalScore source) {
        this.target = target;
        this.operation = operation;
        this.source = source;

        target.assertObjectiveNotNull();
        source.assertObjectiveNotNull();

        if(target.getHolder() == null) {
            source.assertHolderNotNull();
        } else if(source.getHolder() == null) {
            target.assertHolderNotNull();
        }
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players operation " + target.holderToString() + " " + target.objectiveToString() + " " + operation.getShorthand() + " " + source.holderToString() + " " + source.objectiveToString());
    }

}
