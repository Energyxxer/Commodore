package com.energyxxer.mctech.textcomponents.events;

import com.energyxxer.mctech.CommandUtils;
import com.energyxxer.mctech.textcomponents.TextEvent;

public class ClickEvent extends TextEvent {
    public enum Action {
        RUN_COMMAND, SUGGEST_COMMAND, OPEN_URL
    };

    private Action action;
    private String value;

    public ClickEvent(Action action, String value) {
        this.action = action;
        this.value = value;
    }

    @Override
    public String toString() {
        return "\"clickEvent\":{\"action\":\"" +
                action.toString().toLowerCase() +
                "\",\"value\":\"" +
                CommandUtils.escape(value) +
                "\"}";
    }
}
