package com.energyxxer.mctech.textcomponents.events;

import com.energyxxer.mctech.CommandUtils;
import com.energyxxer.mctech.textcomponents.TextComponent;
import com.energyxxer.mctech.textcomponents.TextEvent;

public class HoverEvent extends TextEvent {
    public enum Action {
        SHOW_TEXT, SHOW_ADVANCEMENT, SHOW_ENTITY
    }

    private Action action;
    private String value;

    public HoverEvent(Action action, String value) {
        this.action = action;
        this.value = value;
    }

    public HoverEvent(Action action, TextComponent value) {
        this.action = action;
        this.value = value.toString();
    }

    @Override
    public String toString() {
        return "\"hoverEvent\":{\"action\":\"" +
                action.toString().toLowerCase() +
                "\",\"value\":\"" +
                CommandUtils.escape(value) +
                "\"}";
    }
}
