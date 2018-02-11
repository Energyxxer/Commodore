package com.energyxxer.commodore.textcomponents.events;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.textcomponents.TextEvent;

public class ClickEvent extends TextEvent {
    public enum Action {
        RUN_COMMAND, SUGGEST_COMMAND, OPEN_URL;
    }

    private final Action action;
    private final String value;

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
