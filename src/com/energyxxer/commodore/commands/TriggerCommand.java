package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;

public class TriggerCommand implements Command {

    public enum Action {
        SET, ADD
    }

    private String objective;
    private Action action;
    private int amount;

    public TriggerCommand(String objective) {
        this(objective, Action.ADD, 1);
    }

    public TriggerCommand(String objective, Action action, int amount) {
        this.objective = objective;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "trigger " + objective + (action != Action.ADD || amount != 1 ? " " + action.toString().toLowerCase() + " " + amount : "");
    }
}
