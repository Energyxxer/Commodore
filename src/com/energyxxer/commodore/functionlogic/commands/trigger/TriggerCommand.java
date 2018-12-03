package com.energyxxer.commodore.functionlogic.commands.trigger;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.functions.FunctionSection;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TriggerCommand implements Command {

    public enum Action {
        SET(false), ADD(true);

        final boolean readsScore;

        Action(boolean readsScore) {
            this.readsScore = readsScore;
        }
    }

    private Objective objective;
    private Action action;
    private int amount;

    private ExecutionContext execContext;
    private ArrayList<ScoreboardAccess> accesses;

    public TriggerCommand(Objective objective) {
        this(objective, Action.ADD, 1);
    }

    public TriggerCommand(Objective objective, Action action, int amount) {
        if(!objective.getType().equals("trigger"))
            throw new IllegalArgumentException("Unable to use objective '" + objective.getName() + "' with TriggerCommand; Expected objective of type 'trigger', instead got '" + objective.getType() + "'");
        this.objective = objective;
        this.action = action;
        this.amount = amount;
    }

    private void createScoreboardAccesses() {
        if(accesses != null) return;
        accesses = new ArrayList<>();

        LocalScore localScore = new LocalScore(objective, execContext.getFinalSender());

        if(action.readsScore) {
            ScoreboardAccess access2 = new ScoreboardAccess(localScore.getMacroScores(), ScoreboardAccess.AccessType.WRITE);
            ScoreboardAccess access1 = new ScoreboardAccess(localScore.getMacroScores(), ScoreboardAccess.AccessType.READ, access2);
            accesses.add(access1);
            accesses.add(access2);
        } else {
            accesses.add(new ScoreboardAccess(localScore.getMacroScores(), ScoreboardAccess.AccessType.WRITE));
        }
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        if(accesses == null) createScoreboardAccesses();
        return accesses;
    }

    @Override
    public void onAppend(FunctionSection section, ExecutionContext execContext) {
        this.execContext = execContext;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "trigger " + objective.getName() + (action != Action.ADD || amount != 1 ? " " + action.toString().toLowerCase() + " " + amount : ""));
    }
}
